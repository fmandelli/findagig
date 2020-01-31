package findagig.batch.job;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class GigsWriter implements ItemWriter<Long> {

    @Override
    public void write(List<? extends Long> list) throws Exception {
        for (Long val : list) {
            System.out.println("Writing: " + val);
        }

    }
}
