package findagig.batch.domain.factory;

import findagig.batch.domain.entity.Artist;
import findagig.batch.domain.entity.Gig;
import findagig.batch.domain.entity.Location;
import findagig.batch.domain.entity.Venue;
import findagig.batch.source.entity.Event;
import findagig.batch.source.entity.Performance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.logstash.logback.argument.StructuredArguments.keyValue;

public class GigsFactory {

    /**
     * Creates a List of Gigs from an Event object
     *
     * @param event is an Event that FindAGig API is consuming
     *              information from in order to create Gigs
     * @return a List of Gigs
     */

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<Gig> createGigs(Event event) {

        List<Gig> gigs = new ArrayList<>();
        Stream<Performance> performances = event.getPerformance().stream();

        performances.forEach(performance -> {
            Gig gig = new Gig();
            findagig.batch.source.entity.Artist srcArtist = performance.getArtist();

            /* Gig ID needs to be set as Performance ID, otherwise two or more
               Artists performing in the same Event would generate duplicated Gig ID
               (in case Event ID was used to set Gig ID) */
            gig.setId(performance.getId());
            gig.setSourceURI(event.getUri());
            gig.setDisplayName(event.getDisplayName());
            gig.setStartDateTime(convertStartDateTime(event));
            gig.setStatus(event.getStatus().toString());
            gig.setType(event.getType().toString());

            gig.setArtist(this.createArtist(srcArtist));

            if (event.getVenue() != null) {
                gig.setVenue(this.createVenue(event.getVenue()));
            }
            gigs.add(gig);

        });
        return gigs;
    }

    private LocalDateTime convertStartDateTime(Event event) {
        return LocalDateTime
                .parse(event.getStart().getDate() + "T" +
                                (event.getStart().getTime() == null ? "00:00" : event.getStart().getTime()),
                        DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    /**
     * Creates a List of Gig objects from a List of Event objects
     *
     * @param events List of Event objects
     * @return List of Gig objects
     */
    public List<Gig> createGigs(List<Event> events) {
        return events.stream()
                .map(ev -> createGigs(ev))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }


    /**
     * Creates an Artist object (FindAGig API) from an Event object
     *
     * @param sourceArtist is an Artist object that exists within an Event object
     * @return an Artist object belonging to the FindAGig API
     */
    private Artist createArtist(findagig.batch.source.entity.Artist sourceArtist) {
        Artist artist = new Artist();
        artist.setId(sourceArtist.getId());
        artist.setDisplayName(sourceArtist.getDisplayName());
        artist.setSummary("");
        artist.setWebsite("");
        artist.setSourceURI(sourceArtist.getUri());
        artist.setMusicBrainzs(sourceArtist.getIdentifier());
        return artist;
    }


    /**
     * Creates a Venue object (FindAGig API) from an Event object
     *
     * @param sourceVenue is a Venue object that exists within an Event object
     * @return a Venue object to be used within the FindAGig API
     */
    private Venue createVenue(findagig.batch.source.entity.Venue sourceVenue) {
        Venue venue = new Venue();
        venue.setId(sourceVenue.getId() != null ? sourceVenue.getId() : 0);
        venue.setDescription(sourceVenue.getDescription());
        venue.setDisplayName(sourceVenue.getDisplayName());
        venue.setWebsite(sourceVenue.getWebsite());
        venue.setStreet(sourceVenue.getStreet());
        venue.setZipCode(sourceVenue.getZip());
        venue.setLatitude(sourceVenue.getLat());
        venue.setLongitude(sourceVenue.getLng());
        venue.setSourceURI(sourceVenue.getUri());

        Location location = new Location();
        location.setCity(sourceVenue.getMetroArea().getDisplayName());
        location.setState(sourceVenue.getMetroArea().getState().getDisplayName());
        location.setCountry(sourceVenue.getMetroArea().getCountry().getDisplayName());
        venue.setLocation(location);
        return venue;
    }
}
