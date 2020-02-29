package findagig.batch.domain.factory;

import findagig.batch.domain.entity.Artist;
import findagig.batch.domain.entity.City;
import findagig.batch.domain.entity.Gig;
import findagig.batch.domain.entity.Venue;
import findagig.batch.domain.vo.Country;
import findagig.source.entity.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GigsFactory {

    /**
     * Creates a List of Gigs from an Event object
     * @param event is an Event that FindAGig API is consuming
     *              information from in order to create Gigs
     * @return a List of Gigs
     */
    public List<Gig> createGigs(Event event) {

        List<Gig> gigs = new ArrayList<Gig>();

        event.getArtists().forEach(artist -> {
            Gig gig = new Gig();
            gig.setId(new Random().nextLong());
            gig.setSourceURI(event.getUri());
            gig.setDisplayName(event.getDisplayName());
            gig.setStartDateTime(event.getStartDateTime());
            gig.setEndDateTime(event.getEndDateTime());
            gig.setStatus(event.getStatus().toString());
            gig.setType(event.getType().toString());

            gig.setArtist(this.createArtist(artist));

            gig.setVenue(this.createVenue(event.getVenue()));
            gigs.add(gig);
        });
        return gigs;
    }


    /**
     * Creates an Artist object (FindAGig API) from an Event object
     * @param sourceArtist is an Artist object that exists within an Event object
     * @return an Artist object belonging to the FindAGig API
     */
    private Artist createArtist(findagig.source.entity.Artist sourceArtist) {
        Artist artist = new Artist();
        artist.setId(sourceArtist.getId());
        artist.setDisplayName(sourceArtist.getDisplayName());
        artist.setSummary("");
        artist.setWebsite("");
        artist.setSourceURI(sourceArtist.getUri());
        return artist;
    }


    /**
     * Creates a Venue object (FindAGig API) from an Event object
     * @param sourceVenue is a Venue object that exists within an Event object
     * @return a Venue object to be used within the FindAGig API
     */
    private Venue createVenue(findagig.source.entity.Venue sourceVenue) {
        Venue venue = new Venue();
        venue.setId(sourceVenue.getId());
        venue.setDescription("");
        venue.setDisplayName(sourceVenue.getDisplayName());
        venue.setWebsite(sourceVenue.getWebsite());
        venue.setStreet(sourceVenue.getStreet());
        venue.setZipCode(sourceVenue.getZip());
        venue.setLatitude(sourceVenue.getLatitude());
        venue.setLongitude(sourceVenue.getLongitude());
        venue.setSourceURI(sourceVenue.getUri());

        Country country = new Country(new Random().nextInt(), sourceVenue.getCountry());
        City city = new City();
        city.setDisplayName(sourceVenue.getCity());
        city.setCountry(country);
        city.setId(new Random().nextInt());
        venue.setCity(city);
        return venue;
    }
}
