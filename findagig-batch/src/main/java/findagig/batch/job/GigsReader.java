package findagig.batch.job;

import findagig.source.driver.SongKickDriver;
import findagig.source.entity.Event;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.time.LocalDate;
import java.util.List;

public class GigsReader implements ItemReader<Event> {

    private List<Event> songKickEvents;

    public GigsReader() {
        this.songKickEvents = new SongKickDriver().getEventsByLocationFromDate("Winnipeg,", LocalDate.now());;
    }

    @Override
    public Event read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (!this.songKickEvents.isEmpty()) {
            System.out.println("<<<  PROCESSSING THE FOLLOWING EVENT FROM SOURCE >>>" + this.songKickEvents.get(0).getId());
            return this.songKickEvents.remove(0);
        }
        System.out.println("<<< NO EVENT(S) TO BE PROCESSED >>>");
        return null;
    }
}
