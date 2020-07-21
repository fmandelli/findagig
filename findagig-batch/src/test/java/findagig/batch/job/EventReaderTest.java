package findagig.batch.job;

import findagig.batch.source.driver.SongKickDriver;
import findagig.batch.source.entity.Event;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class EventReaderTest {

    @InjectMocks
    private EventReader eventReader = new EventReader();

    @Mock
    private SongKickDriver driver;

    @Test
    void noEvents() throws Exception {
        when(driver.getUpcomingEventsByMetroAreaId(27403L, 1)).thenReturn(new ArrayList<>());
        assertNull(eventReader.read());
        verify(driver, times(1)).getUpcomingEventsByMetroAreaId(27403L, 1);
    }

    @Test
    void onePageResults() throws Exception {
        Event event = new Event();

        when(driver.getUpcomingEventsByMetroAreaId(27403L, 1)).thenReturn(Collections.singletonList(event));
        when(driver.getUpcomingEventsByMetroAreaId(27403L, 2)).thenReturn(new ArrayList<>());

        assertNotNull(eventReader.read());
        verify(driver, times(1)).getUpcomingEventsByMetroAreaId(27403L, 1);

        assertNull(eventReader.read());
        verify(driver, times(1)).getUpcomingEventsByMetroAreaId(27403L, 2);
    }

    @Test
    void twoPageResults() throws Exception {

        when(driver.getUpcomingEventsByMetroAreaId(27403L, 1)).thenReturn(Collections.singletonList(new Event()));
        when(driver.getUpcomingEventsByMetroAreaId(27403L, 2)).thenReturn(Collections.singletonList(new Event()));
        when(driver.getUpcomingEventsByMetroAreaId(27403L, 3)).thenReturn(new ArrayList<>());

        assertNotNull(eventReader.read());
        verify(driver, times(1)).getUpcomingEventsByMetroAreaId(27403L, 1);

        assertNotNull(eventReader.read());
        verify(driver, times(1)).getUpcomingEventsByMetroAreaId(27403L, 2);

        assertNull(eventReader.read());
        verify(driver, times(1)).getUpcomingEventsByMetroAreaId(27403L, 3);
    }

}