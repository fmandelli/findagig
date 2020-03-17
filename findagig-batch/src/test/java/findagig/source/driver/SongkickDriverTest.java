package findagig.source.driver;

import findagig.batch.domain.entity.Gig;
import findagig.source.entity.Event;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertTrue;

class SongkickDriverTest {

    @Test
    void getEventsByLocationFromDate() {
        SongKickDriver driver = new SongKickDriver();
        List<Event> events = driver.getEventsByLocationFromDate("Munchen", LocalDate.now(), 1);
        assertTrue(events != null);
    }


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
        assertTrue(winnipegEvents.get(0).getVenue().getMetroArea().getDisplayName().equalsIgnoreCase("Winnipeg"));
    }

}