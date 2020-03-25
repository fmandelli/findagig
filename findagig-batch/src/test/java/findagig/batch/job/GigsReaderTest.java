package findagig.batch.job;

import findagig.source.entity.Event;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GigsReaderTest {

    @Test
    void read() throws Exception {
        GigsReader reader = new GigsReader();
        Event event = reader.read();
        assertTrue(event != null);
    }
}