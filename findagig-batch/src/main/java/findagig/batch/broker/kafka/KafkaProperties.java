package findagig.batch.broker.kafka;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class KafkaProperties {

    public static Properties getProperties(StreamProperties sp) {
        Properties props = new Properties();

        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, sp.getBrokers());
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.LongSerde.class);
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, JsonSerde.class);
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, sp.getAppId());
        props.put(StreamsConfig.CLIENT_ID_CONFIG, sp.getClientId());
        return props;
    }

    public static Properties getPropertiesWithSecurity(KafkaSecurityProperties securityProperties, StreamProperties sp) {
        Properties properties = getProperties(sp);
        if (securityProperties != null) {
            properties.putAll(securityProperties.getProperties());
        }
        return properties;
    }

}
