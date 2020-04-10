package findagig.batch.job;

import findagig.source.entity.Event;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;

class GigsWriterTest {

    @Test
    void write() throws Exception {
        GigsReader reader = new GigsReader();
        GigsWriter writer = new GigsWriter();
        Event event = reader.read();
        List<Event> list = new ArrayList<>();
        list.add(event);
        assertAll(() -> writer.write(list));
    }
}