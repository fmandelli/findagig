package findagig.batch.source.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SongKickProperties {

    @Value("${songkick.api.endpoint.address}")
    private String endpointAddress;

    @Value("${songkick.api.key}")
    private String apiKey;

    @Value("${songkick.api.url.metroarea.upcomingEvents}")
    private String upcomingEventsByMetroAreaIdURL;

    @Value("${songkick.api.url.location.nameSearch}")
    private String searchByLocationNameURL;


    public SongKickProperties() {}


    public String getEndpointAddress() {
        return endpointAddress;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getUpcomingEventsByMetroAreaIdURL() {
        return upcomingEventsByMetroAreaIdURL;
    }

    public String getSearchByLocationNameURL() {
        return searchByLocationNameURL;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SongKickProperties{");
        sb.append("endpointAddress='").append(endpointAddress).append('\'');
        sb.append(", apiKey='").append(apiKey).append('\'');
        sb.append(", upcomingEventsByMetroAreaIdURL='").append(upcomingEventsByMetroAreaIdURL).append('\'');
        sb.append(", searchByLocationNameURL='").append(searchByLocationNameURL).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
