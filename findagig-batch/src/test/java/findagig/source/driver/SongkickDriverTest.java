package findagig.source.driver;

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

}