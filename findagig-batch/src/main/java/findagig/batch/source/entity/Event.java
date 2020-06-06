package findagig.batch.source.entity;

import java.util.List;

public class Event {
    private Long id;
    private Type type;
    private String uri;
    private String displayName;
    private List<Performance> performance;
    private Venue venue;
    private Status status;
    private Schedule start;

    public class Schedule {
        private String date;
        private String time;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Schedule{");
            sb.append("date='").append(date).append('\'');
            sb.append(", time='").append(time).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    public enum Status {
        OK, CANCELLED, POSTPONED
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

    public List<Performance> getPerformance() {
        return performance;
    }

    public void setPerformance(List<Performance> performance) {
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

    public Schedule getStart() {
        return start;
    }

    public void setStart(Schedule start) {
        this.start = start;
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
        sb.append(", start=").append(start);
        sb.append('}');
        return sb.toString();
    }
}
