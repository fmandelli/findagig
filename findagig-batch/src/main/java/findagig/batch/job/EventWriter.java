package findagig.batch.job;

import findagig.batch.broker.kafka.EventProducer;
import findagig.batch.source.entity.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.keyValue;

@Service
public class EventWriter implements ItemWriter<Event> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EventProducer eventProducer;

    public EventWriter() {
    }

    @Override
    public void write(List<? extends Event> events) throws Exception {
        long time = System.currentTimeMillis();
        events.stream().forEach(event -> {
            try {
                eventProducer.produce(event);
                logger.info("Event written on destination",
                        keyValue("event", "EVENT_WRITE"),
                        keyValue("EVENT_ID", event.getId()),
                        keyValue("EVENT_DISPLAY-NAME", event.getDisplayName()),
                        keyValue("duration", System.currentTimeMillis() - time));
            }
            catch (Exception e) {
                logger.error("Error on writing an Event object on destination",
                        keyValue("event", "EVENT_ERROR"),
                        keyValue("JSON_OBJECT", event.toString()),
                        keyValue("EXCEPTION", e.toString()));
            }
        });
    }
}
