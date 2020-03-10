package findagig.source.driver;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import findagig.batch.domain.entity.Gig;
import findagig.source.entity.Artist;
import findagig.source.entity.Event;
import findagig.source.entity.Venue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
public class SongKickDriver {

    public static final String SONG_KICK_ENDPOINT_ADDRESS = "https://api.songkick.com/api/3.0";
    public static final String SONG_KICK_API_KEY = "EDaoxv9PhlnV2HYy";
    private int maxExecutions = new Random().nextInt(55);
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Gets a List of Events from SongKick (temporarily providing mocking information)
     *
     * @param city     from the Events will take place
     * @param fromDate starting date of the search
     * @return
     */
    public List<Event> getEventsByLocationFromDate(String city, LocalDate fromDate, int fromPage) {

        List<Event> events = new ArrayList<>();
        if (fromPage > maxExecutions) {
            return events;
        }

        int howMany = new Random().nextInt(10);

        System.out.println("<<< NUMBER OF EVENT(S) CONSUMED FROM SOURCE >>> " + howMany);

        for (int i = 0; i < howMany; i++) {
            Event e = new Event();
            //e.setArtists(createArtist());
            e.setDisplayName("Event From Hell #" + i);
            //e.setEndDateTime(LocalDateTime.now().plusMonths(3));
            //e.setStartDateTime(LocalDateTime.now());
            e.setId(new Random().nextLong());
            //e.setStatus(Event.Status.OK);
            e.setType(Event.Type.CONCERT);
            //e.setVenue(createVenue(city));
            events.add(e);
        }
        return events;
    }


    /**
     * Creates a Venue (mock)
     *
     * @param city is the Venue location
     * @return
     */
    private Venue createVenue(String city) {
        Venue venue = new Venue();
        //venue.setCity(city);
        //venue.setCountry("DE");
        venue.setDisplayName(city);
        venue.setLat(new Random().nextFloat());
        venue.setLng(new Random().nextFloat());
        venue.setId(new Random().nextLong());
        //venue.setStreet("Westendstr. 170");
        //venue.setWebsite("www.venue.ca");

        return venue;
    }


    /**
     * Creates an Artist (mock)
     *
     * @return a List containing the created Artist
     */
    private List<Artist> createArtist() {
        Artist artist = new Artist();
        artist.setDisplayName("Husker Du");
        artist.setId(new Random().nextLong());
        artist.setUri("www.huskers");

        List<Artist> artists = new ArrayList<>();
        artists.add(artist);
        return artists;
    }


    /**
     * Search for locations by name using full text search
     *
     * @param locationName is the name of a city. Example of how this information should
     *                     be provided: Winnipeg, London, San+Francisco
     * @return it is TEMPORARILY returning a String containing a Response from SongKick API
     */
    public String getLocationsByName(String locationName) {

        StringBuilder uri = new StringBuilder(SONG_KICK_ENDPOINT_ADDRESS);
        uri.append("/search/locations.json?query=").append(locationName);
        uri.append("&apikey=").append(SONG_KICK_API_KEY);

        try {
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(uri.toString(), String.class);

            logger.info("Requested locations by name from source.", keyValue("sourceApi", "songKick"), keyValue("sourceApiUrl", uri.toString()));

            System.out.println(result);

            return result;
        } catch (Exception e) {
            logger.error("Error getting locations from source.", keyValue("sourceApi", "songKick"), keyValue("sourceApiUrl", uri.toString()), keyValue("error", e.toString()));
        }
        return null;
    }


    /**
     * Get upcoming Events from a Metro Area
     * @param metroAreaId is a SongKick MetroAreaId
     * @return List of upcoming Events of a Metro Area
     */
    public List<Event> getUpcomingEventsByMetroAreaId(long metroAreaId) {

        StringBuilder uri = new StringBuilder(SONG_KICK_ENDPOINT_ADDRESS);
        uri.append("/metro_areas/").append(metroAreaId);
        uri.append("/calendar.json?apikey=").append(SONG_KICK_API_KEY);

        try {
            //File json = new File("/home/fmandelli/development/projects/winnipeg-events.json");
            RestTemplate restTemplate = new RestTemplate();
            String json = restTemplate.getForObject(uri.toString(), String.class);

            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);

            JsonNode node = mapper.readTree(json);
            List<JsonNode> eventNode = node.findValues("event");
            //Event[] events = mapper.convertValue(eventNode.get(0), Event[].class);
            List<Event> events = mapper.convertValue(eventNode.get(0), new TypeReference<List<Event>>() {});
            logger.info("Response of upcoming Events by MetroAreaId", keyValue("event", "EVENT_HTTP_RESPONSE"), keyValue("METRO_AREA_ID", metroAreaId), keyValue("sourceApi", "songKick"), keyValue("sourceApiUrl", uri.toString()));
            return events;

        } catch (IOException e) {
            logger.error("Error on getting response of upcoming Events by MetroAreaId", keyValue("event", "EVENT_ERROR"), keyValue("METRO_AREA_ID", metroAreaId), keyValue("sourceApi", "songKick"), keyValue("sourceApiUrl", uri.toString()));
        }
        return null;
    }
}
