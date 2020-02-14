package findagig.source.driver;

import findagig.source.entity.Artist;
import findagig.source.entity.Event;
import findagig.source.entity.Venue;

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
}
