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

public class SongKickGigsFactory implements GigsFactory<Event> {

    /**
     * Creates a List of Gigs from the SongKick Event object
     * @param event is an Event from the SongKick API
     * @return a List of Gigs
     */
    @Override
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
     * Creates an Artist object (FindAGig API) from a SongKick Artist object
     * @param songKickArtist is an Artist object from SongKick API
     * @return an Artist object to be used within the FindAGig API
     */
    private Artist createArtist(findagig.source.entity.Artist songKickArtist) {
        Artist artist = new Artist();
        artist.setId(songKickArtist.getId());
        artist.setDisplayName(songKickArtist.getDisplayName());
        artist.setSummary("");
        artist.setWebsite("");
        artist.setSourceURI(songKickArtist.getUri());
        return artist;
    }


    /**
     * Creates a Event object (FindAGig API) from a SongKick Event object
     * @param songKickVenue is a Venue object from SongKick API
     * @return a Venue object to be used within the FindAGig API
     */
    private Venue createVenue(findagig.source.entity.Venue songKickVenue) {
        Venue venue = new Venue();
        venue.setId(songKickVenue.getId());
        venue.setDescription("");
        venue.setDisplayName(songKickVenue.getDisplayName());
        venue.setWebsite(songKickVenue.getWebsite());
        venue.setStreet(songKickVenue.getStreet());
        venue.setZipCode(songKickVenue.getZip());
        venue.setLatitude(songKickVenue.getLatitude());
        venue.setLongitude(songKickVenue.getLongitude());
        venue.setSourceURI(songKickVenue.getUri());

        Country country = new Country(new Random().nextInt(), songKickVenue.getCountry());
        City city = new City();
        city.setDisplayName(songKickVenue.getCity());
        city.setCountry(country);
        city.setId(new Random().nextInt());
        venue.setCity(city);
        return venue;
    }
}
