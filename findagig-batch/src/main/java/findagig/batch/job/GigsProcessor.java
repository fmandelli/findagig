package findagig.batch.job;

import findagig.batch.domain.entity.Gig;
import findagig.batch.domain.factory.SongKickGigsFactory;
import findagig.source.entity.Event;
import org.springframework.batch.item.ItemProcessor;

import java.util.ArrayList;
import java.util.List;

public class GigsProcessor implements ItemProcessor<Event, List<Gig>> {

    SongKickGigsFactory songKickGigsFactory = new SongKickGigsFactory();
    List<Gig> gigs = new ArrayList<Gig>();

    @Override
    public List<Gig> process(Event songKickEvent) throws Exception {
        this.gigs = this.songKickGigsFactory.createGigs(songKickEvent);
        return this.gigs;
    }

}
