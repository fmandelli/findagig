package findagig.batch.job;

import findagig.batch.domain.entity.*;
import findagig.batch.domain.vo.Country;
import findagig.source.entity.Event;
import org.springframework.batch.item.ItemProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GigsProcessor implements ItemProcessor<Event, List<Gig>> {

    List<Gig> gigs = new ArrayList<Gig>();

    @Override
    public List<Gig> process(Event songKickEvent) throws Exception {
        this.gigs = this.parse(songKickEvent);
        return this.gigs;
    }


    /**
     * Parses a musical Event from SongKick to either a single or multiple Gigs,
     * depending on the number of artists participating in a such Event.
     * @param ev is an Event object from SongKick
     * @return a List of Gig objects
     */
    private List<Gig> parse(Event ev) {

        List<Gig> list = new ArrayList<Gig>();

        ev.getArtists().forEach(a -> {
            Gig gig = new Gig();

            gig.setId(new Random().nextLong());

            Artist artist = new Artist();
            artist.setId(a.getId());
            artist.setName(a.getDisplayName());
            artist.setSummary("");
            artist.setWebsite("");
            artist.setSourceURI(a.getUri());
            gig.setArtist(artist);

            Venue venue = new Venue();
            Country country = new Country(new Random().nextInt(), ev.getVenue().getCountry());
            City city = new City();
            city.setName(ev.getVenue().getCity());
            city.setCountry(country);
            city.setId(new Random().nextInt());
            venue.setCity(city);

            venue.setDescription(ev.getVenue().getDisplayName() + " - Address:" + ev.getVenue().getStreet());
            venue.setName(ev.getVenue().getDisplayName());
            venue.setId(ev.getVenue().getId());
            venue.setSourceURI(ev.getVenue().getUri());
            gig.setVenue(venue);

            list.add(gig);
        });
        return list;
    }
}
