package findagig.batch.job;

import findagig.source.driver.SongKickDriver;
import findagig.source.entity.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.logstash.logback.argument.StructuredArguments.keyValue;

@Service
public class EventReader implements ItemReader<Event> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private List<Event> events = new ArrayList<>();
    private SongKickDriver driver;
    private Map<String, Integer> metroAreas = new HashMap<>();
    private int page = 0;

    public EventReader() {
        driver = new SongKickDriver();

        //At this point, only events from the below cities will be collected.
        //The HashMap below contains a key/value of City and MetroAreaId
        metroAreas.put("WINNIPEG", 27403);
        //metroAreas.put("TORONTO", 27396);
        //metroAreas.put("VANCOUVER", 27398);
        //metroAreas.put("MUNICH", 28549);
    }

    @Override
    public Event read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (this.events.isEmpty()) {
            page++;
            if (page > 1) // no loop. returns only the first page
                return null;
            events = readEvents(page);
        }
        return this.events.remove(0);
    }

    private List<Event> readEvents(int fromPage) {
        metroAreas.forEach((key, value) -> {
            long time = System.currentTimeMillis();
            List<Event> newList = driver.getUpcomingEventsByMetroAreaId(value);
            logger.info("Events read",
                    keyValue("event", "EVENT_READ"),
                    keyValue("METRO_AREA", key),
                    keyValue("duration", System.currentTimeMillis() - time));

            events.addAll(newList);
        });
        return events;
    }

}
