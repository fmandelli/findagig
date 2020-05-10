package findagig.batch.job;

import findagig.source.entity.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventReaderTest {

    @Test
    void readSingleEvent() throws Exception {
        EventReader reader = new EventReader();
        Event event = reader.read();
        assertTrue(event != null);
    }

}