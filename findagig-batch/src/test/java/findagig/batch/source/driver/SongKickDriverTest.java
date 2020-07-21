package findagig.batch.source.driver;

import findagig.batch.source.entity.Event;
import findagig.batch.source.entity.MetroArea;
import findagig.batch.source.entity.Venue;
import findagig.batch.source.properties.SongKickProperties;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SongKickDriverTest {

    @InjectMocks
    private SongKickDriver driver;

    @Mock
    private SongKickProperties properties = new SongKickProperties();

    //@Mock
    //private RestTemplate restTemplate = new RestTemplate();

    @Test
    void moreThanOneLocationByNameFound() {
        String uri = "https://api.songkick.com/api/3.0/" +
                "search/locations.json?" +
                "query={location_name}" +
                "&apikey=EDaoxv9PhlnV2HYy" +
                "&page={pageNum}";

        when(properties.getSearchByLocationNameURL()).thenReturn(uri);
        List<MetroArea> areas = driver.getLocationsByName("LONDON");

        assertNotNull(areas);
        Assert.assertThat(areas.size(), Matchers.greaterThan(1));
    }


    @Test
    void getOnePageOfUpcomingEventsByWinnipegCanadaArea() {
        String uri = "https://api.songkick.com/api/3.0/" +
                "metro_areas/{metro_area_id}/calendar.json?" +
                "apikey=EDaoxv9PhlnV2HYy" +
                "&page={pageNum}";
        when(properties.getUpcomingEventsByMetroAreaIdURL()).thenReturn(uri);
        List<Event> events = driver.getUpcomingEventsByMetroAreaId(27403L, 1);
        Assert.assertNotNull("Number of Event objects returned is " + events.size(), events);
        Assert.assertThat(events.size(), Matchers.greaterThan(10));
    }


    @Test
    void getVenueByIdInWinnipeg() {
        String uri = "https://api.songkick.com/api/3.0/" +
                "venues/{venue_id}.json?" +
                "apikey=EDaoxv9PhlnV2HYy";

        when(properties.getSearchVenueByIdURL()).thenReturn(uri);
        Venue venue = driver.getVenueById(3050414L);
        Assert.assertNotNull(venue);
    }
}
