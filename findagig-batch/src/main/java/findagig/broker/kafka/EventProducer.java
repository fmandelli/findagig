package findagig.broker.kafka;

import findagig.source.entity.Event;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.logstash.logback.argument.StructuredArguments.keyValue;

/**
 * Class responsible for producing an Event object on Kafka's Event topic
 *
 * @author Flavio Mandelli
 * @version 1.0
 */
public class EventProducer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String KAFKA_EVENT_TOPIC = "rsybt2u0-event";
    private KafkaProducer kafkaProducer;


    public EventProducer() {
        kafkaProducer = new KafkaConfiguration().buildProducer();
    }


    /**
     * Produces an Event on Kafka's Event topic
     * @param event an Event object to be produced on the topic
     */
    public void produce(Event event) {
        try {
            kafkaProducer.send(new ProducerRecord(KAFKA_EVENT_TOPIC, event.getId(), event));
            logger.info("Event object successfully produced on Kafka topic",
                    keyValue("event", "EVENT_KAFKA_PRODUCE"),
                    keyValue("TOPIC", KAFKA_EVENT_TOPIC),
                    keyValue("JSON_OBJECT", event.toString()));
        }
        catch (Exception e) {
            logger.error("Error on producing an Event object on Kafka topic",
                    keyValue("event", "EVENT_KAFKA_PRODUCE_ERROR"),
                    keyValue("TOPIC", KAFKA_EVENT_TOPIC),
                    keyValue("JSON_OBJECT", event.toString()),
                    keyValue("EXCEPTION", e.toString()));
        }

    }

}
