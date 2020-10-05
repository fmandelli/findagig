package findagig.batch.broker.kafka;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest(classes = GigStreamProperties.class)
@ActiveProfiles("local")
class GigStreamPropertiesTest {

    @Autowired
    private GigStreamProperties kafkaProperties;

    @Test
    void validateAllKafkaPropertiesContent() {
        List<String> brokers = kafkaProperties.getBrokers();

        assertTrue(brokers.size() == 1);
        assertTrue(kafkaProperties.getClientId().startsWith(new String("gig.findagig.cli")));
        assertTrue(kafkaProperties.getAppId().startsWith("gig.findagig.app"));
        assertEquals(kafkaProperties.getTopic(), "gig");
        assertNull(kafkaProperties.getToTopic());
    }
}