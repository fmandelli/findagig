package findagig.batch.job;

import findagig.batch.domain.entity.Gig;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class GigsWriter implements ItemWriter<List<Gig>> {

    @Override
    public void write(List<? extends List<Gig>> list) throws Exception {

        list.stream().forEach(gigs -> {
            gigs.stream().forEach(gig -> {
                System.out.println("Gig " + gig);
            });
        });

    }
}
