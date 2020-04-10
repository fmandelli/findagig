package findagig.batch.broker.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@ConfigurationProperties(prefix = "kafka")
public class KafkaProperties {

    @Value("${kafka.credentials.username}")
    private String usernane;

    @Value("${kafka.credentials.password}")
    private String password;

    @Value("${kafka.cluster.brokers}")
    private List<String> brokers = new ArrayList<>();

    @Value("${kafka.cluster.topic.event}")
    private String eventTopic;

    @Value("${kafka.security.sasl.ssl.security.protocol}")
    private String saslSslSecurityProtocol;

    @Value("${kafka.security.scram.sha256.mechanism}")
    private String scramSha256Mechanism;


    public KafkaProperties() {
    }

    public String getUsernane() {
        return usernane;
    }

    public void setUsernane(String usernane) {
        this.usernane = usernane;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getBrokers() {
        return brokers;
    }

    public void setBrokers(List<String> brokers) {
        this.brokers = brokers;
    }

    public String getEventTopic() {
        return eventTopic;
    }

    public void setEventTopic(String eventTopic) {
        this.eventTopic = eventTopic;
    }

    public String getSaslSslSecurityProtocol() {
        return saslSslSecurityProtocol;
    }

    public void setSaslSslSecurityProtocol(String saslSslSecurityProtocol) {
        this.saslSslSecurityProtocol = saslSslSecurityProtocol;
    }

    public String getScramSha256Mechanism() {
        return scramSha256Mechanism;
    }

    public void setScramSha256Mechanism(String scramSha256Mechanism) {
        this.scramSha256Mechanism = scramSha256Mechanism;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("KafkaProperties{");
        sb.append("usernane='").append(usernane).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", brokers=").append(brokers);
        sb.append(", eventTopic='").append(eventTopic).append('\'');
        sb.append(", saslSslSecurityProtocol='").append(saslSslSecurityProtocol).append('\'');
        sb.append(", scramSha256Mechanism='").append(scramSha256Mechanism).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
