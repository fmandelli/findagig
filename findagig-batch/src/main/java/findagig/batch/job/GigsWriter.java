package findagig.batch.job;

import findagig.batch.domain.entity.Gig;
import findagig.broker.kafka.EventProducer;
import findagig.source.entity.Event;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.keyValue;

public class GigsWriter implements ItemWriter<Event> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private EventProducer eventProducer;

    public GigsWriter() {
        eventProducer = new EventProducer();
    }

    @Override
    public void write(List<? extends Event> events) throws Exception {
        long time = System.currentTimeMillis();
        events.stream().forEach(event -> {
            eventProducer.produce(event);
            logger.info("Event written on destination",
                    keyValue("event", "EVENT_WRITE"),
                    keyValue("EVENT_ID", event.getId()),
                    keyValue("EVENT_DISPLAY-NAME", event.getDisplayName()),
                    keyValue("duration", System.currentTimeMillis() - time));
        });
    }
}
