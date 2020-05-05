package findagig.batch.broker.kafka;

import findagig.batch.domain.entity.Gig;
import findagig.batch.domain.factory.GigsFactory;
import findagig.source.driver.SongKickDriver;
import findagig.source.entity.Event;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Printed;
import org.codehaus.jettison.json.JSONString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Properties;

@Service
public class EventConsumer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final KafkaProperties kafkaProperties;

    @Autowired
    public EventConsumer(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;

        Properties props = new Properties();

        String jaasTemplate = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";";
        String jaasCfg = String.format(jaasTemplate, this.kafkaProperties.getUsernane(), this.kafkaProperties.getPassword());

        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBrokers());
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.LongSerde.class);
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, JsonSerde.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "findagig.source.entity");
        props.put(StreamsConfig.SECURITY_PROTOCOL_CONFIG, this.kafkaProperties.getSaslSslSecurityProtocol());
        props.put(SaslConfigs.SASL_MECHANISM, this.kafkaProperties.getScramSha256Mechanism());
        props.put(SaslConfigs.SASL_JAAS_CONFIG, jaasCfg);
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "event.findagig.app.01");
        props.put(StreamsConfig.CLIENT_ID_CONFIG, "event.findagig.cli.01");

        startConsumer(props);
    }


    /**
     * Consumes objects from the Event topic, and then
     * posts them to the Gig topic (after transformation)
     * @param properties
     */
    public void startConsumer(Properties properties) {
        final StreamsBuilder builder = new StreamsBuilder();
        SongKickDriver driver = new SongKickDriver();
        GigsFactory factory = new GigsFactory();

        builder.stream(kafkaProperties.getEventTopic(), Consumed.with(Serdes.Long(), Serdes.Bytes()))
                .map((key, value) -> {
                    Event event = driver.transformSingle(value.toString());
                    List<Gig> gigs = factory.createGigs(event);
                    return KeyValue.pair(key, gigs);
                })
                .to("rsybt2u0-gig");

        KafkaStreams streams = new KafkaStreams(builder.build(), properties);

        logger.info("Starting event consumer from topic {}", kafkaProperties.getEventTopic());
        streams.start();
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }

}
