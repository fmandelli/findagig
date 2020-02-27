package findagig.batch.job;

import findagig.source.driver.SongKickDriver;
import findagig.source.entity.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.keyValue;

public class GigsReader implements ItemReader<Event> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private List<Event> songKickEvents = new ArrayList<>();
    private SongKickDriver driver;
    private int page = 0;

    public GigsReader() {
        driver = new SongKickDriver();
    }

    @Override
    public Event read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (!this.songKickEvents.isEmpty()) {
            System.out.println("<<<  PROCESSING THE FOLLOWING EVENT FROM SOURCE >>> " + this.songKickEvents.get(0).getId());
            return this.songKickEvents.remove(0);
        } else {
            page++;
            songKickEvents = readNewEvents(page);
            if (songKickEvents.isEmpty()) {
                page = 0;
                return null;
            }
            return this.songKickEvents.remove(0);
        }
    }

    private List<Event> readNewEvents(int fromPage) {
        long time = System.currentTimeMillis();
        List<Event> events = driver.getEventsByLocationFromDate("Winnipeg,", LocalDate.now(), fromPage);
        logger.info("Events page read", keyValue("event", "EVENT_READ"), keyValue("page", fromPage), keyValue("duration", System.currentTimeMillis() - time));
        return events;
    }
}
