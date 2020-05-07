package findagig.source.driver;

import findagig.batch.domain.entity.Gig;
import findagig.batch.domain.factory.GigsFactory;
import findagig.source.entity.Event;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.keyValue;
import static org.junit.Assert.assertTrue;

class SongkickDriverTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    void getLocationsByName() {
        SongKickDriver driver = new SongKickDriver();
        String json = driver.getLocationsByName("Toronto");
        assertTrue(json.length() > 0);
    }


    @Test
    void getUpcomingEventsByMetroAreaId() {
        SongKickDriver driver = new SongKickDriver();
        List<Event> winnipegEvents = driver.getUpcomingEventsByMetroAreaId(27403);
        logger.info("@Test: Get Events by MetroAreaId", keyValue("event", "EVENT_TEST"));
        assertTrue(winnipegEvents.get(0).getVenue().getMetroArea().getDisplayName().equalsIgnoreCase("Winnipeg"));
    }

}