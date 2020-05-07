package findagig.batch.broker.kafka;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest(classes = KafkaProperties.class)
@ActiveProfiles("local")
class KafkaPropertiesTest {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Test
    void validateAllKafkaPropertiesContent() {
        List<String> brokers = kafkaProperties.getBrokers();

        Assert.assertTrue(brokers.size() == 1);
        Assert.assertTrue(kafkaProperties.getClientId().startsWith(new String("event.findagig.cli")));
        Assert.assertTrue(kafkaProperties.getAppId().startsWith("event.findagig.app"));
        Assert.assertEquals(kafkaProperties.getEventTopic(), "event");
        Assert.assertEquals(kafkaProperties.getGigTopic(), "gig");
    }
}