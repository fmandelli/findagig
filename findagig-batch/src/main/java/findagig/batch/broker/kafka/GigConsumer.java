package findagig.batch.broker.kafka;

import findagig.batch.domain.entity.Gig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.GlobalKTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Properties;

import static findagig.batch.broker.kafka.KafkaProperties.getPropertiesWithSecurity;

@Service
public class GigConsumer {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GigStreamProperties streamProperties;

    @Autowired
    private EventStreamProperties eventStreamProperties;

    @Autowired(required = false)
    private KafkaSecurityProperties securityProperties;

    private ReadOnlyKeyValueStore<Long, Gig> gigsStore;

    private static final String GIG_STORE_NAME = "gigs-global-table";

    /**
     * Consumes objects from the Event topic, and then
     * posts them to the Gig topic (after transformation)
     */
    @PostConstruct
    public void startConsumer() {
        Properties properties = getPropertiesWithSecurity(securityProperties, streamProperties);
        final StreamsBuilder builder = new StreamsBuilder();

        JsonSerde<Gig> valueSerde = new JsonSerde();
        valueSerde.configure(
                Map.of(
                        JsonDeserializer.TRUSTED_PACKAGES, "findagig.batch.domain.entity",
                        JsonDeserializer.VALUE_DEFAULT_TYPE, Gig.class
                ), false);

        builder.globalTable(
                streamProperties.getTopic(),
                Consumed.with(Serdes.Long(), valueSerde),
                Materialized.<Long, Gig, KeyValueStore<Bytes, byte[]>>as(GIG_STORE_NAME)
                        .withKeySerde(Serdes.Long())
                        .withValueSerde(valueSerde)
        );

        KafkaStreams streams = new KafkaStreams(builder.build(), properties);

        streams.setStateListener((newState, oldState) -> {
                    // Check when state-store is totally loaded
                    if (newState.equals(KafkaStreams.State.RUNNING) &&
                            oldState.equals(KafkaStreams.State.REBALANCING)) {
                        gigsStore = streams.store(GIG_STORE_NAME, QueryableStoreTypes.keyValueStore());
                        logger.info("{} was loaded with {} gigs", GIG_STORE_NAME, gigsStore.approximateNumEntries());

                        new EventConsumer(eventStreamProperties, securityProperties).startConsumer();

                        if( gigsStore.all().hasNext()) {
                            KeyValue<Long, Gig> g= gigsStore.all().next();
                            logger.info("Gig key {}: {}", g.key, g.value);
                        }
                    }
                }
        );

        logger.info("Starting event consumer from topic {} ", streamProperties.getTopic());
        streams.start();
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }
}


