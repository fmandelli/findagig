package findagig.batch.job;

import findagig.source.entity.Event;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GigsReaderTest {

    @Test
    void read() {
        GigsReader reader = new GigsReader();
        List<Event> allEvents = reader.readEvents();
        assertTrue(allEvents.size() > 50);
    }
}