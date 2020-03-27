package findagig.broker.kafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.streams.StreamsConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Properties;

import static net.logstash.logback.argument.StructuredArguments.keyValue;

/**
 * Sets basic configuration to build and return KafkaProducer and KafkaConsumer objects
 *
 * @author Flavio Mandelli
 * @version 1.0
 */
public class KafkaConfiguration {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String KAFKA_BROKER = "moped-01.srvs.cloudkafka.com:9094,moped-02.srvs.cloudkafka.com:9094,moped-03.srvs.cloudkafka.com:9094";
    private static final String KAFKA_USERNAME = "rsybt2u0";
    private static final String KAFKA_PASSWORD = "qFKp6yDzDj0WE00KxEjha9fHDoeA1B4E";
    private static final String SASL_SSL_SECURITY_PROTOCOL = "SASL_SSL";
    private static final String SCRAM_SHA_256_MECHANISM = "SCRAM-SHA-256";

    private Properties properties = new Properties();
    private KafkaProducer kafkaProducer;
    private KafkaConsumer kafkaConsumer;


    /**
     * Creates a KafkaProducer object instance to send messages to a Kafka topic
     * @return KafkaProducer object
     */
    public KafkaProducer buildProducer() {
        String jaasTemplate = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";";
        String jaasCfg = String.format(jaasTemplate, KAFKA_USERNAME, KAFKA_PASSWORD);
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        properties.put(StreamsConfig.SECURITY_PROTOCOL_CONFIG, SASL_SSL_SECURITY_PROTOCOL);
        properties.put(SaslConfigs.SASL_MECHANISM, SCRAM_SHA_256_MECHANISM);
        properties.put(SaslConfigs.SASL_JAAS_CONFIG, jaasCfg);
        kafkaProducer = new KafkaProducer(properties);
        logger.info("KafkaProducer object successfully created",
                keyValue("event", "EVENT_CREATE"),
                keyValue("OBJECT", kafkaProducer.getClass().getName()));
        return kafkaProducer;
    }


}
