package findagig.source.driver;

import findagig.source.entity.Artist;
import findagig.source.entity.Event;
import findagig.source.entity.Venue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SongkickDriver {

    public List<Event> getEventsByLocationFromDate(String city, LocalDate fromDate) {
        List<Event> events = new ArrayList<>();

        int howMany = new Random().nextInt(10);

        for (int i = 0; howMany < i; i++) {
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
