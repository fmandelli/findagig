package findagig.batch.job;

import findagig.batch.broker.kafka.EventProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EventWriterTest {

    @InjectMocks
    private EventWriter eventWriter;

    @Mock
    private EventProducer eventProducer;

    @Test
    void write() throws Exception {
    }
}