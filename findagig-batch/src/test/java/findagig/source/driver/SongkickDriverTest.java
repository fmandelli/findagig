package findagig.source.driver;

import findagig.source.entity.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

class SongkickDriverTest {

    @Test
    void getEventsByLocationFromDate() {

        SongkickDriver driver = new SongkickDriver();
        List<Event> events = driver.getEventsByLocationFromDate("Munchen", LocalDate.now());
    }
}