package findagig.batch.job;

import findagig.batch.source.driver.SongKickDriver;
import findagig.batch.source.entity.Event;
import findagig.batch.source.entity.Venue;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventProcessorTest {

    @InjectMocks
    private EventProcessor processor = new EventProcessor();

    @Mock
    private SongKickDriver driver;

    @Test
    void processEventContainingVenueMissingInformation() throws Exception {
        String displayName = "DISPLAY-NAME-TEST";

        Venue oldVenue = new Venue();
        oldVenue.setId(3050414L);
        oldVenue.setDisplayName("");

        Venue newVenue = new Venue();
        newVenue.setId(oldVenue.getId());
        newVenue.setDisplayName(displayName);

        Event event = new Event();
        event.setId(1L);
        event.setVenue(oldVenue);

        when(driver.getVenueById(oldVenue.getId())).thenReturn(newVenue);
        event = processor.process(event);

        Assert.assertNotEquals(event.getVenue().getDisplayName(), oldVenue.getDisplayName());
        Assert.assertThat(event.getVenue().getDisplayName(), Matchers.containsString(displayName));
    }
}
