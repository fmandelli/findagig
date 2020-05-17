package findagig.batch.broker.kafka;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


@Configuration
public class KafkaProperties {

    @Value("${kafka.cluster.brokers}")
    private List<String> brokers = new ArrayList<>();

    @Value("${kafka.stream.topic.event}")
    private String eventTopic;

    @Value("${kafka.stream.topic.gig}")
    private String gigTopic;

    @Value("${kafka.stream.app.id}")
    private String appId;

    @Value("${kafka.stream.client.id}")
    private String clientId;

    public KafkaProperties() {
    }

    public List<String> getBrokers() {
        return brokers;
    }

    public String getEventTopic() {
        return eventTopic;
    }

    public String getGigTopic() {
        return gigTopic;
    }

    public String getAppId() {
        return appId;
    }

    public String getClientId() {
        return clientId;
    }


    public Properties getProperties() {
        Properties props = new Properties();

        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.LongSerde.class);
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, JsonSerde.class);
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, appId);
        props.put(StreamsConfig.CLIENT_ID_CONFIG, clientId);
        return props;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("KafkaProperties{");
        sb.append("brokers=").append(brokers);
        sb.append(", eventTopic='").append(eventTopic).append('\'');
        sb.append(", gigTopic='").append(gigTopic).append('\'');
        sb.append(", appId='").append(appId).append('\'');
        sb.append(", clientId='").append(clientId).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public Properties addSecurity(KafkaSecurityProperties securityProperties) {
        Properties properties = getProperties();
        if (securityProperties != null) {
            properties.putAll(securityProperties.getProperties());
        }
        return properties;
    }
}
