package findagig.batch.broker.kafka;

import findagig.batch.domain.entity.Gig;
import findagig.batch.domain.factory.GigsFactory;
import findagig.source.entity.Event;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static net.logstash.logback.argument.StructuredArguments.keyValue;

@Service
public class EventConsumer {

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
        GigsFactory factory = new GigsFactory();

        JsonSerde<Event> valueSerde = new JsonSerde();
        valueSerde.configure(Map.of(JsonDeserializer.TRUSTED_PACKAGES, "findagig.source.entity"), false);

        builder.stream(kafkaProperties.getEventTopic(), Consumed.with(Serdes.Long(), valueSerde))
                .flatMapValues((event) -> {
                            List<Gig> gigs = factory.createGigs(event);
                            gigs.stream().forEach(gig ->
                                    logger.info("Event object successfully produced on Kafka topic",
                                            keyValue("event", "GIG_KAFKA_PRODUCED"),
                                            keyValue("topic", this.kafkaProperties.getGigTopic()),
                                            keyValue("eventId", event.getId()),
                                            keyValue("gigId", gig.getId()),
                                            keyValue("gigArtist", gig.getArtist().getId()),
                                            keyValue("JSON_OBJECT", event.toString())));
                            return gigs;
                        }
                )
                .to(kafkaProperties.getGigTopic(), Produced.with(Serdes.Long(), new JsonSerde<Gig>()));

        KafkaStreams streams = new KafkaStreams(builder.build(), properties);

        logger.info("Starting event consumer from topic {} to {} topic", kafkaProperties.getEventTopic(), kafkaProperties.getGigTopic());
        streams.start();
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }

}
