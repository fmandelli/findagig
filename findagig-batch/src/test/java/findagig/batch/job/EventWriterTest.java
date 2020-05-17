package findagig.batch.job;

import findagig.batch.broker.kafka.EventProducer;
import findagig.batch.broker.kafka.KafkaProperties;
import findagig.batch.source.driver.SongKickDriver;
import findagig.batch.source.entity.Event;
import findagig.batch.source.properties.SongKickProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest(classes = {EventReader.class,
                           EventWriter.class,
                           SongKickDriver.class,
                           SongKickProperties.class,
                           EventProducer.class,
                           KafkaProperties.class})
@ActiveProfiles("local")
class EventWriterTest {

    @Autowired
    EventReader eventReader;

    @Autowired
    EventWriter eventWriter;

    @Test
    void write() throws Exception {
        Event event = eventReader.read();
        List<Event> list = new ArrayList<>();
        list.add(event);
        assertAll(() -> eventWriter.write(list));
    }
}