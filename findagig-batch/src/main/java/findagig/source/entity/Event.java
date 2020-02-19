package findagig.source.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Event {
    private Long id;
    private String displayName;
    private Type type;
    private String uri;
    private Venue venue;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Status status;
    private List<Artist> artists;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(final String displayName) {
        this.displayName = displayName;
    }

    public Type getType() {
        return type;
    }

    public void setType(final Type type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(final String uri) {
        this.uri = uri;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(final Venue venue) {
        this.venue = venue;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(final LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(final LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(final List<Artist> artists) {
        this.artists = artists;
    }

    public enum Status {
        OK, CANCELLED
    }

    public enum Type {
        CONCERT, FESTIVAL
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Event{");
        sb.append("id=").append(id);
        sb.append(", displayName='").append(displayName).append('\'');
        sb.append(", type=").append(type);
        sb.append(", uri='").append(uri).append('\'');
        sb.append(", venue=").append(venue);
        sb.append(", startDateTime=").append(startDateTime);
        sb.append(", endDateTime=").append(endDateTime);
        sb.append(", status=").append(status);
        sb.append(", artists=").append(artists);
        sb.append('}');
        return sb.toString();
    }
}
