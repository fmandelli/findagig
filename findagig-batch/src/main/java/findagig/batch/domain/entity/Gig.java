package findagig.batch.domain.entity;

import findagig.batch.source.entity.MusicBrainz;

import java.time.LocalDateTime;

/**
 * Gig is an Entity/Bean class that represents a Gig on "Find a Gig" database
 *
 * @author Flavio A. Mandelli and Diego Irismar da Costa
 * @version 1.0
 */
public class Gig {

    private long id;
    private String sourceURI;
    private String displayName;
    private String startDateTime;
    private String endDateTime;
    private String status;
    private String type;
    private Artist artist;
    private Venue venue;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSourceURI() {
        return sourceURI;
    }

    public void setSourceURI(String sourceURI) {
        this.sourceURI = sourceURI;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Gig{");
        sb.append("id=").append(id);
        sb.append(", sourceURI='").append(sourceURI).append('\'');
        sb.append(", displayName='").append(displayName).append('\'');
        sb.append(", startDateTime='").append(startDateTime).append('\'');
        sb.append(", endDateTime='").append(endDateTime).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", artist=").append(artist);
        sb.append(", venue=").append(venue);
        sb.append('}');
        return sb.toString();
    }
}
