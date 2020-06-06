package findagig.batch.job;

import findagig.batch.source.driver.SongKickDriver;
import findagig.batch.source.entity.Event;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventProcessor implements ItemProcessor<Event, Event> {

    @Autowired
    SongKickDriver songKickDriver;

    @Override
    public Event process(Event event) throws Exception {
        /* In a few cases Venue.ID is NULL at source (SongKick) */
        if (event.getVenue().getId() != null) {
            /* This is necessary because Venue information is incomplete when
             * provided by SongKick and set as a child node.
             * That issue is fixed by getting the current Venue ID and making
             * a new request to the SongKick API to get an updated Venue object */
            event.setVenue(this.songKickDriver.getVenueById(event.getVenue().getId()));
        }
        return event;
    }
}
