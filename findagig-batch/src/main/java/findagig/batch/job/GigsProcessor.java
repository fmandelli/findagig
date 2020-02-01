package findagig.batch.job;

import org.springframework.batch.item.ItemProcessor;

public class GigsProcessor implements ItemProcessor<String, Long> {

    @Override
    public Long process(String s) throws Exception {
        return Long.valueOf(s.hashCode());
    }
}
