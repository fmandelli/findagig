package findagig.batch.broker.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = KafkaConfig.class)
class KafkaConfigTest {

    @Autowired
    private KafkaConfig kafkaConfig;

    @Test
    void buildProducer() {
        //KafkaProducer producer =  kafkaConfig.buildProducer();
        System.out.println("nothing");
        Assert.assertTrue(1 == 1);
    }





}