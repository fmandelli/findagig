package findagig.batch.broker.kafka;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = KafkaSecurityProperties.class)
@ActiveProfiles("prod")
class KafkaSecurityPropertiesTest {

    @Autowired
    private KafkaSecurityProperties securityProperties;

    @Test
    void validateAllKafkaPropertiesContent() {
        Assert.assertTrue(securityProperties.getUsername().equals(new String("rsybt2u0")));
        Assert.assertTrue(securityProperties.getPassword().endsWith("fHDoeA1B4E"));
        Assert.assertTrue(securityProperties.getSaslSslSecurityProtocol().equalsIgnoreCase("SASL_SSL"));
        Assert.assertTrue(securityProperties.getScramSha256Mechanism().equalsIgnoreCase("SCRAM-SHA-256"));
        Assert.assertTrue(securityProperties.getEventTopic().equalsIgnoreCase(securityProperties.getUsername() + "-event"));
    }
}