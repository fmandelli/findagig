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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.logstash.logback.argument.StructuredArguments.keyValue;
import static org.junit.Assert.assertTrue;

class SongKickDriverTest {

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
        List<Integer> pages = Stream.of(1, 2, 3, 4, 5).collect(Collectors.toList());

        pages.forEach(pg -> {
            long metroAreaId = 27403;
            List<Event> winnipegEvents = driver.getUpcomingEventsByMetroAreaId(metroAreaId, pg);
            logger.info("@Test: Get Events by MetroAreaId",
                    keyValue("event", "EVENT_TEST"),
                    keyValue("METRO_AREA_ID", metroAreaId),
                    keyValue("PAGE_NUMBER", pg));
            assertTrue(winnipegEvents.get(0).getVenue().getMetroArea().getDisplayName().equalsIgnoreCase("Winnipeg"));
        });


    }

}