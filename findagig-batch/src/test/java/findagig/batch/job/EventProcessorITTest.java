package findagig.batch.job;

import findagig.batch.source.driver.SongKickDriver;
import findagig.batch.source.entity.Event;
import findagig.batch.source.entity.Venue;
import findagig.batch.source.properties.SongKickProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {EventProcessor.class, SongKickDriver.class, SongKickProperties.class})
@ActiveProfiles("local")
class EventProcessorITTest {

    @Autowired
    EventProcessor eventProcessor;

    @Test
    void getUpdatedEventVenueFromSource() throws Exception {
        Event before = new Event();
        Venue venue = new Venue();
        venue.setId(30032L);
        before.setVenue(venue);
        Event after = eventProcessor.process(before);
        assertTrue(after.getVenue().getId().equals(before.getVenue().getId()));
        assertTrue(after.getVenue().getZip().matches("R3B 2H2"));
    }

}