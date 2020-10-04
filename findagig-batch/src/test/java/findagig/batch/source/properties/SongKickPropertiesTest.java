package findagig.batch.source.properties;

import findagig.batch.source.properties.SongKickProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SongKickProperties.class)
@ActiveProfiles("local")
class SongKickPropertiesTest {

    @Autowired
    private SongKickProperties properties;

    @Test
    void validateEndpointAddressAndApiKey() {
        List<String> entries = Arrays.asList(properties.getApiKey(),
                                             properties.getEndpointAddress());
        assertAll("entries",
                () -> assertTrue(properties.getEndpointAddress().equals("https://api.songkick.com/api/3.0")),
                () -> assertTrue(properties.getApiKey().equals("EDaoxv9PhlnV2HYy"))
        );
    };


    @Test
    void validateURLs() {
        List<String> urls = Arrays.asList(properties.getSearchByLocationNameURL("x", 1),
                                          properties.getUpcomingEventsByMetroAreaIdURL(1L, 1));
        assertAll("urls",
                () -> assertTrue(urls.get(0).startsWith(properties.getEndpointAddress())),
                () -> assertTrue(urls.get(0).contains(properties.getApiKey())),
                () -> assertTrue(urls.get(1).startsWith(properties.getEndpointAddress())),
                () -> assertTrue(urls.get(1).contains(properties.getApiKey()))
        );
    }
}