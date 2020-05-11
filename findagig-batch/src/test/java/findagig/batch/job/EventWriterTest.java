package findagig.batch.job;

import findagig.source.entity.Event;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;

class EventWriterTest {

    @Test
    void write() throws Exception {
        EventReader reader = new EventReader();
        EventWriter writer = new EventWriter();
        Event event = reader.read();
        List<Event> list = new ArrayList<>();
        list.add(event);
        assertAll(() -> writer.write(list));
    }
}