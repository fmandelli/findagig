package findagig.batch.job;

import findagig.batch.domain.entity.Gig;
import findagig.source.entity.Event;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.keyValue;

public class GigsWriter implements ItemWriter<Event> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void write(List<? extends Event> items) throws Exception {
        long time = System.currentTimeMillis();
        items.stream().forEach(item -> {
            logger.info("Event was written", keyValue("event", "EVENT_WRITE"), keyValue("EVENT_ID", item.getId()), keyValue("EVENT_DISPLAY-NAME", item.getDisplayName()), keyValue("duration", System.currentTimeMillis() - time));
        });
    }
}
