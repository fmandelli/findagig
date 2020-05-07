package findagig.batch.broker.kafka;

import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Configuration
@Profile("prod")
public class KafkaSecurityProperties extends KafkaProperties{

    @Value("${kafka.credentials.username}")
    private String username;

    @Value("${kafka.credentials.password}")
    private String password;

    @Value("${kafka.security.sasl.ssl.security.protocol}")
    private String saslSslSecurityProtocol;

    @Value("${kafka.security.scram.sha256.mechanism}")
    private String scramSha256Mechanism;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSaslSslSecurityProtocol() {
        return saslSslSecurityProtocol;
    }

    public String getScramSha256Mechanism() {
        return scramSha256Mechanism;
    }

    public Properties getProperties() {
        Properties props = new Properties();

        String jaasTemplate = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";";
        String jaasCfg = String.format(jaasTemplate, username, password);

        props.put(StreamsConfig.SECURITY_PROTOCOL_CONFIG, saslSslSecurityProtocol);
        props.put(SaslConfigs.SASL_MECHANISM, scramSha256Mechanism);
        props.put(SaslConfigs.SASL_JAAS_CONFIG, jaasCfg);

        return props;
    }

}
