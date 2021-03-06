package findagig.batch.source.driver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import findagig.batch.source.entity.Event;
import findagig.batch.source.entity.MetroArea;
import findagig.batch.source.entity.Venue;
import findagig.batch.source.properties.SongKickProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
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
    private RestTemplate restTemplate = new RestTemplate();
    private static final ObjectMapper mapper = new ObjectMapper();

    {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    }

    /**
     * Search for locations by name using full text search
     *
     * @param locationName is the name of a city. Example of how this information should
     *                     be provided: Winnipeg, London, San+Francisco
     * @return list of MetroArea
     */
    public List<MetroArea> getLocationsByName(String locationName) {

        String uri = songKickProperties.getSearchByLocationNameURL(locationName, 1);

        try {
            String result = callExternalAPI(uri);
            JsonNode resultJson = mapper.readTree(result);

            JsonNode locationNode = resultJson.findValue("location");
            Iterator<JsonNode> iteratorLocals = locationNode.iterator();
            List<MetroArea> areas = new ArrayList<>();
            while (iteratorLocals.hasNext()){
               JsonNode node = iteratorLocals.next();
               MetroArea area = mapper.convertValue(node.get("metroArea"), MetroArea.class );
               areas.add(area);
            }

            logger.info("Successfully got a response of Locations by name from source.",
                    keyValue("EVENT", "EVENT_HTTP_RESPONSE"),
                    keyValue("SOURCE-API", "SONGKICK"),
                    keyValue("SOURCE-API-URL", uri));

            return areas;
        } catch (Exception e) {
            logger.error("Error on getting a response of Locations by name from source.",
                    keyValue("EVENT", "EVENT_ERROR"),
                    keyValue("SOURCE-API", "SONGKICK"),
                    keyValue("SOURCE-API-URL", uri),
                    keyValue("ERROR", e.toString()));
            throw new RuntimeException(e);
        }
    }


    /**
     * Get upcoming Events from a Metro Area
     *
     * @param metroAreaId is a SongKick MetroAreaId
     * @return List of upcoming Events of a Metro Area
     */
    public List<Event> getUpcomingEventsByMetroAreaId(Long metroAreaId, Integer pageNumber) {

        String uri = songKickProperties.getUpcomingEventsByMetroAreaIdURL(metroAreaId, pageNumber);

        try {
            String json = callExternalAPI(uri);

            logger.info("Response of upcoming Events by MetroAreaId.",
                    keyValue("EVENT", "EVENT_HTTP_RESPONSE"),
                    keyValue("METRO_AREA_ID", metroAreaId),
                    keyValue("PAGE_NUMBER", pageNumber),
                    keyValue("SOURCE-API", "SONGKICK"),
                    keyValue("SOURCE-API-URL", uri),
                    keyValue("JSON_OBJECT_RETURNED", json));

            return transformMany(json);
        } catch (Exception e) {
            logger.error("Error on getting response of upcoming Events by MetroAreaId.",
                    keyValue("EVENT", "EVENT_ERROR"),
                    keyValue("METRO_AREA_ID", metroAreaId),
                    keyValue("SOURCE-API", "SONGKICK"),
                    keyValue("SOURCE-API-URL", uri),
                    keyValue("EXCEPTION", e.toString()));
        }
        return null;
    }



    /**
     * Transforms a JSON object representing an Event into a FindAGig Event object
     *
     * @param jsonEventObj represents an Event object in a JSON format
     * @return List of Event objects
     */
    public List<Event> transformMany(String jsonEventObj) {

        try {
            JsonNode node = mapper.readTree(jsonEventObj);
            List<JsonNode> eventNode = node.findValues("event");
            List<Event> events = mapper.convertValue(eventNode.get(0), new TypeReference<List<Event>>() {
            });

            logger.info("JSON object successfully mapped into List of Event objects.",
                    keyValue("EVENT", "EVENT_TRANSFORM"),
                    keyValue("LIST_SIZE", events.size()));

            return events;
        } catch (JsonMappingException e) {
            logger.error("Error when mapping JSON object into Event object.",
                    keyValue("EVENT", "EVENT_ERROR"),
                    keyValue("JSON_OBJECT_NOT_CONVERTED", jsonEventObj),
                    keyValue("EXCEPTION", e.toString()));
        } catch (JsonProcessingException e) {
            logger.error("Error when processing JSON object.",
                    keyValue("EVENT", "EVENT_ERROR"),
                    keyValue("JSON_OBJECT_NOT_CONVERTED", jsonEventObj),
                    keyValue("EXCEPTION", e.toString()));
        }
        return null;
    }


    /**
     * Gets a Venue JSON object by its ID
     *
     * @param venueId is a Venue ID
     * @return a Venue object
     */
    public Venue getVenueById(Long venueId) {
        /* replaces parameter in the url */
        String uri = songKickProperties.getSearchVenueByIdURL(venueId);

        try {
            String json = callExternalAPI(uri);

            logger.info("Response of searching Venue by ID.",
                    keyValue("EVENT", "EVENT_HTTP_RESPONSE"),
                    keyValue("VENUE_ID", venueId),
                    keyValue("SOURCE-API", "SONGKICK"),
                    keyValue("SOURCE-API-URL", uri),
                    keyValue("JSON_OBJECT_RETURNED", json));
            return transformSingleVenueJSON(json);

        } catch (Exception e) {
            logger.error("Error on response from getting a Venue by ID.",
                    keyValue("EVENT", "EVENT_ERROR"),
                    keyValue("VENUE_ID", venueId),
                    keyValue("SOURCE-API", "SONGKICK"),
                    keyValue("SOURCE-API-URL", uri),
                    keyValue("EXCEPTION", e.toString()));
        }
        return null;
    }


    /**
     * Transforms a single Venue JSON object into a Venue object
     *
     * @param jsonVenueObj is a JSON object representing a Venue
     * @return a Venue object
     */
    public Venue transformSingleVenueJSON(String jsonVenueObj) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);

        try {
            JsonNode node = mapper.readTree(jsonVenueObj);
            JsonNode venueNode = node.findValue("venue");
            Venue venue = mapper.treeToValue(venueNode, Venue.class);
            logger.info("JSON object successfully mapped into single Venue object.",
                    keyValue("event", "TRANSFORM"),
                    keyValue("OUTPUT_OBJECT", venue.toString()));
            return venue;

        } catch (JsonMappingException e) {
            logger.error("Error when mapping JSON object into Venue object.",
                    keyValue("EVENT", "EVENT_ERROR"),
                    keyValue("JSON_OBJECT_NOT_CONVERTED", jsonVenueObj),
                    keyValue("EXCEPTION", e.toString()));
        } catch (JsonProcessingException e) {
            logger.error("Error when processing JSON object.",
                    keyValue("EVENT", "EVENT_ERROR"),
                    keyValue("JSON_OBJECT_NOT_CONVERTED", jsonVenueObj),
                    keyValue("EXCEPTION", e.toString()));
        }
        return null;
    }


    /**
     * Call external SongKick API
     * @param uri
     * @return
     */
    private String callExternalAPI(String uri) {
        return this.restTemplate.getForObject(uri, String.class);
    }
}
