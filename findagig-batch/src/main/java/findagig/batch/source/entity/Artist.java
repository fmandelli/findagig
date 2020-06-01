package findagig.batch.source.entity;

import java.util.List;

public class Artist {
    private Long id;
    private String displayName;
    private String uri;

    /* MusicBrainz identifiers for this artist.
       It is possible that an artist has multiple MusicBrainz IDs.
       if we are not sure which is correct
       As SongKick is not sure which ID is correct, an array is provided. */
    private List<MusicBrainz> identifier;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<MusicBrainz> getIdentifier() {
        return identifier;
    }

    public void setIdentifier(List<MusicBrainz> identifier) {
        this.identifier = identifier;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Artist{");
        sb.append("id=").append(id);
        sb.append(", displayName='").append(displayName).append('\'');
        sb.append(", uri='").append(uri).append('\'');
        sb.append(", identifier=").append(identifier);
        sb.append('}');
        return sb.toString();
    }
}
