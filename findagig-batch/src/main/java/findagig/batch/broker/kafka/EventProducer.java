package findagig.batch.broker.kafka;

import findagig.batch.source.entity.Event;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Properties;

import static findagig.batch.broker.kafka.KafkaProperties.getPropertiesWithSecurity;
import static net.logstash.logback.argument.StructuredArguments.keyValue;

/**
 * Class responsible for producing an Event object on Kafka's Event topic
 *
 * @author Flavio Mandelli
 * @version 1.0
 */
@Service
public class EventProducer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private KafkaProducer kafkaProducer;

    @Autowired
    private EventStreamProperties streamProperties;

    @Autowired(required = false)
    private KafkaSecurityProperties securityProperties;

    @PostConstruct
    public void startProducer() {
        Properties properties = getPropertiesWithSecurity(securityProperties, streamProperties);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        kafkaProducer = new KafkaProducer(properties);
    }

    /**
     * Produces an Event on Kafka's Event topic
     *
     * @param event an Event object to be produced on the topic
     */
    public void produce(Event event) throws Exception {
        try {
            kafkaProducer.send(new ProducerRecord(this.streamProperties.getTopic(), event.getId(), event));
            logger.info("Event object successfully produced on Kafka topic",
                    keyValue("event", "EVENT_KAFKA_PRODUCE"),
                    keyValue("TOPIC", this.streamProperties.getTopic()),
                    keyValue("JSON_OBJECT", event.toString()));
        } catch (Exception e) {
            logger.error("Error on producing an Event object on Kafka topic",
                    keyValue("event", "EVENT_KAFKA_PRODUCE_ERROR"),
                    keyValue("TOPIC", this.streamProperties.getTopic()),
                    keyValue("JSON_OBJECT", event.toString()),
                    keyValue("EXCEPTION", e.toString()));
        }
    }
}
