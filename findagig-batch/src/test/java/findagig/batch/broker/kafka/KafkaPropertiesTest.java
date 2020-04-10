package findagig.batch.broker.kafka;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = KafkaProperties.class)
class KafkaPropertiesTest {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Test
    void validateAllKafkaPropertiesContent() {
        List<String> brokers = kafkaProperties.getBrokers();

        Assert.assertTrue(brokers.size() == 3);
        Assert.assertTrue(kafkaProperties.getUsernane().equals(new String("rsybt2u0")));
        Assert.assertTrue(kafkaProperties.getPassword().endsWith("fHDoeA1B4E"));
        Assert.assertTrue(kafkaProperties.getSaslSslSecurityProtocol().equalsIgnoreCase("SASL_SSL"));
        Assert.assertTrue(kafkaProperties.getScramSha256Mechanism().equalsIgnoreCase("SCRAM-SHA-256"));
        Assert.assertTrue(kafkaProperties.getEventTopic().equalsIgnoreCase(kafkaProperties.getUsernane() + "-event"));
    }
}