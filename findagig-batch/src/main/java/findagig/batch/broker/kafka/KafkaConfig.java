package findagig.batch.broker.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Sets basic configuration to build and return KafkaProducer and KafkaConsumer objects
 *
 * @author Flavio Mandelli
 * @version 1.0
 */
@Component
public class KafkaConfig {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KafkaProperties kafkaProperties;

//    private final String KAFKA_BROKER = environment.getProperty("kafka.broker");
//    private final String KAFKA_USERNAME = environment.getProperty("kafka.username");
//    private final String KAFKA_PASSWORD = environment.getProperty("kafka.password");
//    private final String SASL_SSL_SECURITY_PROTOCOL = environment.getProperty("kafka.sasl.ssl.security.protocol");
//    private final String SCRAM_SHA_256_MECHANISM = environment.getProperty("kafka.scram.mechanism.sha256");


    private Properties properties = new Properties();
    private KafkaProducer kafkaProducer;


    public KafkaConfig() {
//        KAFKA_BROKER = environment.getProperty("kafka.broker");
//        KAFKA_USERNAME = environment.getProperty("kafka.username");
//        KAFKA_PASSWORD = environment.getProperty("kafka.password");
//        SASL_SSL_SECURITY_PROTOCOL = environment.getProperty("kafka.sasl.ssl.security.protocol");
//        SCRAM_SHA_256_MECHANISM = environment.getProperty("kafka.scram.mechanism.sha256");
        System.out.println("KAFKA_BROKER ");
    }

    /**
     * Creates a KafkaProducer object instance to send messages to a Kafka topic
     * @return KafkaProducer object
     */
    public KafkaProducer buildProducer() {
        System.out.println("test ");
//        String jaasTemplate = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";";
//        String jaasCfg = String.format(jaasTemplate, KAFKA_USERNAME, KAFKA_PASSWORD);
//        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER);
//        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
//        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//        properties.put(StreamsConfig.SECURITY_PROTOCOL_CONFIG, SASL_SSL_SECURITY_PROTOCOL);
//        properties.put(SaslConfigs.SASL_MECHANISM, SCRAM_SHA_256_MECHANISM);
//        properties.put(SaslConfigs.SASL_JAAS_CONFIG, jaasCfg);
        kafkaProducer = new KafkaProducer(properties);
//        logger.info("KafkaProducer object successfully created",
//                keyValue("event", "EVENT_CREATE"),
//                keyValue("OBJECT", kafkaProducer.getClass().getName()));
        return kafkaProducer;
    }





}
