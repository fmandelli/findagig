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

    @Value("${songkick.api.url.venue.nameSearch}")
    private String searchVenueByNameURL;

    @Value("${songkick.api.url.venue.idSearch}")
    private String searchVenueByIdURL;


    public SongKickProperties() {}


    public String getEndpointAddress() {
        return endpointAddress;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getUpcomingEventsByMetroAreaIdURL(Long metroAreaId, Integer pageNumber) {
        return upcomingEventsByMetroAreaIdURL
                .replace("{metro_area_id}", metroAreaId.toString())
                .replace("{pageNum}", pageNumber.toString());
    }

    public String getSearchByLocationNameURL(String locationName, Integer pageNumber) {
        return searchByLocationNameURL
                .replace("{location_name}", locationName)
                .replace("{pageNum}", pageNumber.toString());
    }

    public String getSearchVenueByNameURL(String venueName) {
        return searchVenueByNameURL
                .replace("{venue_name}", venueName);
    }

    public String getSearchVenueByIdURL(Long venueId) {
        return searchVenueByIdURL
                .replace("{venue_id}", venueId.toString());
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SongKickProperties{");
        sb.append("endpointAddress='").append(endpointAddress).append('\'');
        sb.append(", apiKey='").append(apiKey).append('\'');
        sb.append(", upcomingEventsByMetroAreaIdURL='").append(upcomingEventsByMetroAreaIdURL).append('\'');
        sb.append(", searchByLocationNameURL='").append(searchByLocationNameURL).append('\'');
        sb.append(", searchVenueByNameURL='").append(searchVenueByNameURL).append('\'');
        sb.append(", searchVenueByIdURL='").append(searchVenueByIdURL).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
