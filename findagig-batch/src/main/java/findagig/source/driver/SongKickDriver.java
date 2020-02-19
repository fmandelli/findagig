package findagig.source.driver;

import findagig.source.entity.Artist;
import findagig.source.entity.Event;
import findagig.source.entity.Venue;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * SongKickDriver is class used to parse information from SongKick to java entities that represent
 * objects from the SongKick API
 *
 * This class is temporarily a mocking class
 *
 * @author Diego Irismar da Costa
 * @version 1.0
 */
public class SongKickDriver {

    public static final String SONG_KICK_ENDPOINT_ADDRESS = "https://demo5448093.mockable.io";
    public static final String SONG_KICK_API_KEY = "9876543210";


    /**
     * Gets a List of Events from SongKick (temporarily providing mocking information)
     * @param city from the Events will take place
     * @param fromDate starting date of the search
     * @return
     */
    public List<Event> getEventsByLocationFromDate(String city, LocalDate fromDate) {

        List<Event> events = new ArrayList<>();

        int howMany = new Random().nextInt(10);

        System.out.println("<<< NUMBER OF EVENT(S) CONSUMED FROM SOURCE >>> " + howMany);

        for (int i = 0; i < howMany; i++) {
            Event e = new Event();
            e.setArtists(createArtist());
            e.setDisplayName("Event From Hell #" + i);
            e.setEndDateTime(LocalDateTime.now().plusMonths(3));
            e.setStartDateTime(LocalDateTime.now());
            e.setId(new Random().nextLong());
            e.setStatus(Event.Status.OK);
            e.setType(Event.Type.CONCERT);
            e.setVenue(createVenue(city));
            events.add(e);
        }
        return events;
    }


    /**
     * Creates a Venue (mock)
     * @param city is the Venue location
     * @return
     */
    private Venue createVenue(String city) {
        Venue venue = new Venue();
        venue.setCity(city);
        venue.setCountry("DE");
        venue.setDisplayName(city);
        venue.setLatitude(new Random().nextFloat());
        venue.setLatitude(new Random().nextFloat());
        venue.setId(new Random().nextLong());
        venue.setStreet("Westendstr. 170");
        venue.setWebsite("www.venue.ca");

        return venue;
    }


    /**
     * Creates an Artist (mock)
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
     * Searches for one more Events in a specific SongKick's MetroAreaId
     * @param metroAreaId
     * @return it is TEMPORARILY returning a String containing a Response from SongKick API
     */
    public String findEventByMetroAreaId(String metroAreaId) {

        System.out.println("<<< Getting Events by MetroAreaId=" + metroAreaId + " >>>");

        try {
            StringBuilder uri = new StringBuilder(SONG_KICK_ENDPOINT_ADDRESS);
            uri.append("/metro_areas/").append(metroAreaId);
            uri.append("/calendar.json?");
            uri.append("apikey=").append(SONG_KICK_API_KEY);

            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(uri.toString(), String.class);

            System.out.println(result);

            return result;
        }
        catch (Exception e) {
            System.out.println("ERROR: " + this.getClass().getName() + ". " + e.getStackTrace()[0].getMethodName()) ;
        }
        return null;
    }
}
