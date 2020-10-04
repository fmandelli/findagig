package findagig.batch.source.driver;

import com.fasterxml.jackson.core.JsonProcessingException;
import findagig.batch.source.entity.Event;
import findagig.batch.source.entity.Venue;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SongKickDriverTest {

    @InjectMocks
    private SongKickDriver driver;


    @Test
    void parseSingleJSONVenueIntoEntityVenue() throws JsonProcessingException {
        String apiResponse = "{\"resultsPage\":{\"status\":\"ok\",\"results\":{\"venue\":{\"id\":3050414,\"displayName\":\"Club Regent Event Centre\",\"uri\":\"http://www.songkick.com/venues/3050414-club-regent-event-centre?utm_source=59625&utm_medium=partner\",\"metroArea\":{\"displayName\":\"Winnipeg\",\"country\":{\"displayName\":\"Canada\"},\"state\":{\"displayName\":\"MB\"},\"id\":27403,\"uri\":\"http://www.songkick.com/metro_areas/27403-canada-winnipeg?utm_source=59625&utm_medium=partner\"},\"lat\":49.89644,\"lng\":-97.04486,\"street\":\"1425 Regent Avenue West\",\"zip\":\"R2C 3B2\",\"phone\":null,\"website\":null,\"description\":\"\",\"capacity\":null,\"city\":{\"displayName\":\"Winnipeg\",\"country\":{\"displayName\":\"Canada\"},\"state\":{\"displayName\":\"MB\"},\"id\":27403,\"uri\":\"http://www.songkick.com/metro_areas/27403-canada-winnipeg?utm_source=59625&utm_medium=partner\"}}}}}";
        Venue expected = new Venue();
        expected.setId(3050414L);
        Venue actual = driver.transformSingleVenueJSON(apiResponse);
        Assert.assertEquals(expected.getId(), actual.getId());
    }

    @Test
    void parseManyJSONEventsIntoEventList() {
        String apiResponse200 = "{\"resultsPage\":{\"status\":\"ok\",\"results\":{\"event\":[";
        // creates 50 occurrences of events into json response
        for (int i = 0; i < 50; i++) {
             apiResponse200 += "{\"id\":39631274,\"displayName\":\"Black Horizon at Park Theatre (July 30, 2020)\",\"type\":\"Concert\",\"uri\":\"https://www.songkick.com/concerts/39631274-black-horizon-at-park-theatre?utm_source=59625&utm_medium=partner\",\"status\":\"ok\",\"popularity\":2.3e-05,\"start\":{\"date\":\"2020-07-30\",\"datetime\":\"2020-07-30T21:30:00-0500\",\"time\":\"21:30:00\"},\"performance\":[{\"id\":74960767,\"displayName\":\"Black Horizon\",\"billing\":\"headline\",\"billingIndex\":1,\"artist\":{\"id\":419301,\"displayName\":\"Black Horizon\",\"uri\":\"https://www.songkick.com/artists/419301-black-horizon?utm_source=59625&utm_medium=partner\",\"identifier\":[{\"mbid\":\"10c692c8-8ccb-4f13-97c7-59b813a1f865\",\"href\":\"https://api.songkick.com/api/3.0/artists/mbid:10c692c8-8ccb-4f13-97c7-59b813a1f865.json\"},{\"mbid\":\"01264de5-2c09-47f0-86b1-6198c81dedda\",\"href\":\"https://api.songkick.com/api/3.0/artists/mbid:01264de5-2c09-47f0-86b1-6198c81dedda.json\"}]}}],\"ageRestriction\":null,\"flaggedAsEnded\":false,\"venue\":{\"id\":35624,\"displayName\":\"Park Theatre\",\"uri\":\"https://www.songkick.com/venues/35624-park-theatre?utm_source=59625&utm_medium=partner\",\"metroArea\":{\"displayName\":\"Winnipeg\",\"country\":{\"displayName\":\"Canada\"},\"state\":{\"displayName\":\"MB\"},\"id\":27403,\"uri\":\"https://www.songkick.com/metro-areas/27403-canada-winnipeg?utm_source=59625&utm_medium=partner\"},\"lat\":49.86225,\"lng\":-97.13167},\"location\":{\"city\":\"Winnipeg, MB, Canada\",\"lat\":49.86225,\"lng\":-97.13167}}";
             if (i < 49) {
                 // no comma after last json event
                 apiResponse200 += ",";
             }
        }
        apiResponse200 += "]},\"perPage\":50,\"page\":1,\"totalEntries\":50}}";

        List<Event> events = driver.transformMany(apiResponse200);
        Assert.assertTrue(events.size() == 50);
    }
}
