package findagig.batch.source.driver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import findagig.batch.source.properties.SongKickProperties;
import findagig.batch.source.entity.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.keyValue;

/**
 * SongKickDriver is class used to parse information from SongKick to java entities that represent
 * objects from the SongKick API
 * <p>
 * This class is temporarily a mocking class
 *
 * @author Diego Irismar da Costa, Flavio A. Mandelli
 * @version 1.0
 */
@Service
public class SongKickDriver {

    @Autowired
    private SongKickProperties songKickProperties;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     *
     * Search for locations by name using full text search
     *
     * @param locationName is the name of a city. Example of how this information should
     *                     be provided: Winnipeg, London, San+Francisco
     * @param pageNumber page number being requested
     * @return it is TEMPORARILY returning a String containing a Response from SongKick API
     */
    public String getLocationsByName(String locationName, int pageNumber) {

        String uri = songKickProperties.getSearchByLocationNameURL()
                .replace("{location_name}", locationName)
                .replace("{pageNum}", String.valueOf(pageNumber));

        try {
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(uri, String.class);

            logger.info("Requested locations by name from source.",
                    keyValue("sourceApi", "songKick"),
                    keyValue("sourceApiUrl", uri.toString()));

            System.out.println(result);

            return result;
        } catch (Exception e) {
            logger.error("Error getting locations from source.",
                    keyValue("sourceApi", "songKick"),
                    keyValue("sourceApiUrl", uri.toString()),
                    keyValue("error", e.toString()));
        }
        return null;
    }


    /**
     * Get upcoming Events from a Metro Area
     * @param metroAreaId is a SongKick MetroAreaId
     * @return List of upcoming Events of a Metro Area
     */
    public List<Event> getUpcomingEventsByMetroAreaId(long metroAreaId, int pageNumber) {

        String uri = songKickProperties.getUpcomingEventsByMetroAreaIdURL()
                .replace("{metro_area_id}", String.valueOf(metroAreaId))
                .replace("{pageNum}", String.valueOf(pageNumber));

        try {
            RestTemplate restTemplate = new RestTemplate();
            String json = restTemplate.getForObject(uri, String.class);

            logger.info("Response of upcoming Events by MetroAreaId.",
                    keyValue("event", "EVENT_HTTP_RESPONSE"),
                    keyValue("METRO_AREA_ID", metroAreaId),
                    keyValue("PAGE_NUMBER", pageNumber),
                    keyValue("sourceApi", "songKick"),
                    keyValue("sourceApiUrl", uri.toString()),
                    keyValue("JSON_OBJECT_RETURNED", json));

            return transformMany(json);

        } catch (Exception e) {
            logger.error("Error on getting response of upcoming Events by MetroAreaId.",
                    keyValue("event", "EVENT_ERROR"),
                    keyValue("METRO_AREA_ID", metroAreaId),
                    keyValue("sourceApi", "songKick"),
                    keyValue("sourceApiUrl", uri.toString()),
                    keyValue("EXCEPTION", e.toString()));
        }
        return null;
    }


    /**
     * Transforms a JSON object representing an Event into a FindAGig Event object
     * @param jsonEventObj represents an Event object in a JSON format
     * @return List of Event objects
     */
    public List<Event> transformMany(String jsonEventObj) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);

        try {
            JsonNode node = mapper.readTree(jsonEventObj);
            List<JsonNode> eventNode = node.findValues("event");
            List<Event> events = mapper.convertValue(eventNode.get(0), new TypeReference<List<Event>>() {});
            logger.info("JSON object successfully mapped into List of Event objects.",
                    keyValue("LIST_SIZE", events.size()));
            return events;
        } catch (JsonMappingException e) {
            logger.error("Error when mapping JSON object into Event object.",
                    keyValue("event", "EVENT_ERROR"),
                    keyValue("JSON_OBJECT_NOT_CONVERTED", jsonEventObj),
                    keyValue("EXCEPTION", e.toString()));
        } catch (JsonProcessingException e) {
            logger.error("Error when processing JSON object.",
                    keyValue("event", "EVENT_ERROR"),
                    keyValue("JSON_OBJECT_NOT_CONVERTED", jsonEventObj),
                    keyValue("EXCEPTION", e.toString()));
        }
        return null;
    }

}