package findagig.batch.job;

import findagig.batch.source.driver.SongKickDriver;
import findagig.batch.source.entity.Event;
import findagig.batch.source.properties.SongKickProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {EventReader.class, SongKickDriver.class, SongKickProperties.class})
@ActiveProfiles("local")
class EventReaderTest {

    @Autowired
    private EventReader eventReader;

    @Test
    void readSingleEvent() throws Exception {
        Event event = eventReader.read();
        assertTrue(event != null);
    }

}