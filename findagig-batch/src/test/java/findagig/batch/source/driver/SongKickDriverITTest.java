package findagig.batch.source.driver;

import findagig.batch.source.entity.Event;
import findagig.batch.source.entity.MetroArea;
import findagig.batch.source.properties.SongKickProperties;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.logstash.logback.argument.StructuredArguments.keyValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {SongKickProperties.class, SongKickDriver.class})
@ActiveProfiles("local")
class SongKickDriverITTest {

    @Autowired
    SongKickProperties songKickProperties;

    @Autowired
    SongKickDriver songKickDriver;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    void getLocationsByName() {
        List<MetroArea> metroAreas = songKickDriver.getLocationsByName("Toronto");
        assertTrue(metroAreas.size() > 0);
    }

    @Test
    void getUpcomingEventsByMetroAreaId() {
        List<Integer> pages = Stream.of(1, 2, 3, 4, 5)
                .collect(Collectors.toList());

        pages.forEach(pg -> {
            long metroAreaId = 27403;
            List<Event> winnipegEvents = songKickDriver.getUpcomingEventsByMetroAreaId(metroAreaId, pg);
            if (winnipegEvents != null) {
                logger.info("@Test: Get Events by MetroAreaId",
                        keyValue("event", "EVENT_TEST"),
                        keyValue("METRO_AREA_ID", metroAreaId),
                        keyValue("PAGE_NUMBER", pg));
                assertTrue(winnipegEvents.get(0).getVenue().getMetroArea().getDisplayName().equalsIgnoreCase("Winnipeg"));
            }
        });
    }


    @Test
    void validateURLStringSubstitution() {
        List<Integer> parameters = Arrays.asList(2525, 7);

        String url = songKickProperties.getUpcomingEventsByMetroAreaIdURL()
                .replace("{metro_area_id}", String.valueOf(parameters.get(0)))
                .replace("{pageNum}", String.valueOf(parameters.get(1)));

        assertAll("parameters",
                () -> assertTrue(url.contains(parameters.get(0).toString())),
                () -> assertTrue(url.contains(parameters.get(1).toString()))
        );
    }
}