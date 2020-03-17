package findagig.batch.job;

import findagig.batch.domain.entity.Gig;
import findagig.batch.domain.factory.GigsFactory;
import findagig.source.entity.Event;
import org.springframework.batch.item.ItemProcessor;

import java.util.ArrayList;
import java.util.List;

public class GigsProcessor implements ItemProcessor<Event, List<Gig>> {

    GigsFactory gigsFactory = new GigsFactory();
    List<Gig> gigs = new ArrayList<>();

    @Override
    public List<Gig> process(Event event) throws Exception {
        this.gigs = this.gigsFactory.createGigs(event);
        return this.gigs;
    }
}
