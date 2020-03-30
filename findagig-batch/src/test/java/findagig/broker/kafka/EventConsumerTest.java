package findagig.broker.kafka;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventConsumerTest {

    @Test
    void consume() {
        EventConsumer consumer = new EventConsumer();
        consumer.consume();
    }
}