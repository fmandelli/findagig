package findagig.broker.kafka;

import findagig.source.entity.Event;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.streams.StreamsConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class EventConsumer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String KAFKA_EVENT_TOPIC = "rsybt2u0-event";
    private KafkaConsumer kafkaConsumer;

    public EventConsumer() {
        kafkaConsumer = new KafkaConfiguration().buildConsumer();
    }


    public void consume() {
        kafkaConsumer.subscribe(Arrays.asList(KAFKA_EVENT_TOPIC));
        while (true) {
            ConsumerRecords records = kafkaConsumer.poll(Duration.ofMillis(1000));
            //ConsumerRecords<Long, Event> records = kafkaConsumer.poll(Duration.ofMillis(1000));
            for (Object record : records) {
                System.out.printf("Teste" + record);
                //System.out.printf("%s [%d] offset=%d, key=%s, value=\"%s\"\n",
                //        record.topic(), record.partition(),
                //        record.offset(), record.key(), record.value());
            }
        }
    }

}
