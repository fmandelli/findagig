package findagig.batch.broker.kafka;

import findagig.batch.domain.entity.Gig;
import findagig.batch.domain.factory.GigsFactory;
import findagig.batch.source.entity.Event;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import static net.logstash.logback.argument.StructuredArguments.keyValue;

public class EventConsumer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private EventStreamProperties streamProperties;

    private KafkaSecurityProperties securityProperties;

    public EventConsumer(EventStreamProperties streamProperties, KafkaSecurityProperties securityProperties) {
        this.streamProperties = streamProperties;
        this.securityProperties = securityProperties;
    }

    /**
     * Consumes objects from the Event topic, and then
     * posts them to the Gig topic (after transformation)
     */
    public void startConsumer() {
        logger.info("Starting EventConsumer ...");
        Properties properties = KafkaProperties.getPropertiesWithSecurity(securityProperties, streamProperties);

        final StreamsBuilder builder = new StreamsBuilder();
        GigsFactory factory = new GigsFactory();

        JsonSerde<Event> valueSerde = new JsonSerde();
        valueSerde.configure(Map.of(JsonDeserializer.TRUSTED_PACKAGES, "findagig.batch.source.entity"), false);

        builder.stream(streamProperties.getTopic(), Consumed.with(Serdes.Long(), valueSerde))
                .flatMap((aLong, event) -> {
                            List<Gig> gigs = factory.createGigs(event);
                            return gigs.stream()
                                    .map(gig -> new KeyValue<Long, Gig>(gig.getId(), gig))
                                    .collect(Collectors.toList());
                        }
                )
                .filter((aLong, gig) -> true)
                .to(streamProperties.getToTopic(), Produced.with(Serdes.Long(), new JsonSerde<Gig>()));

        KafkaStreams streams = new KafkaStreams(builder.build(), properties);

        logger.info("Starting event consumer from topic {} to {} topic", streamProperties.getTopic(), streamProperties.getToTopic());
        streams.start();
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }

}
