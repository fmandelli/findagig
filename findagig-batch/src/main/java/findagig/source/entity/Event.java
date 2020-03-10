package findagig.source.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Event {
    private Long id;
    private Type type;
    private String uri;
    private String displayName;
    private List<Artist> performance;
    private Venue venue;
    private Status status;

    /*
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;


     */

    public enum Status {
        OK, CANCELLED
    }


    public enum Type {
        CONCERT, FESTIVAL
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<Artist> getPerformance() {
        return performance;
    }

    public void setPerformance(List<Artist> performance) {
        this.performance = performance;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Event{");
        sb.append("id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", uri='").append(uri).append('\'');
        sb.append(", displayName='").append(displayName).append('\'');
        sb.append(", performance=").append(performance);
        sb.append(", venue=").append(venue);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
