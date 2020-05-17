package findagig.batch.broker.kafka;

import findagig.batch.domain.entity.Gig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Printed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Properties;

@Service
public class GigConsumer {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KafkaProperties kafkaProperties;

    @Autowired(required = false)
    private KafkaSecurityProperties securityProperties;

    /**
     * Consumes objects from the Event topic, and then
     * posts them to the Gig topic (after transformation)
     */
    @PostConstruct
    public void startConsumer() {
        Properties properties = kafkaProperties.addSecurity(securityProperties);
        final StreamsBuilder builder = new StreamsBuilder();

        JsonSerde<Gig> valueSerde = new JsonSerde();
        valueSerde.configure(Map.of(JsonDeserializer.TRUSTED_PACKAGES, "findagig.batch.source.entity"), false);

        builder.stream(kafkaProperties.getEventTopic(), Consumed.with(Serdes.Long(), valueSerde))
                .print(Printed.toSysOut());

        KafkaStreams streams = new KafkaStreams(builder.build(), properties);

        logger.info("Starting event consumer from topic {} ", kafkaProperties.getGigTopic());
        streams.start();
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }
}
