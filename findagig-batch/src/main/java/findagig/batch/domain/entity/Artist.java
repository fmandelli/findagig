package findagig.batch.domain.entity;

import findagig.batch.domain.vo.MusicStyle;
import findagig.batch.source.entity.MusicBrainz;

import java.util.List;

/**
 * Class that represents an Artist on the "Find a Gig" API
 *
 * @author Flavio A. Mandelli and Diego Irismar da Costa
 * @version 1.0
 */
public class Artist {

    private long id;
    private String displayName;
    private String website;
    private String summary;
    private List<MusicStyle> musicStyles;
    private String sourceURI;
    private List<MusicBrainz> musicBrainzs;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<MusicStyle> getMusicStyles() {
        return musicStyles;
    }

    public void setMusicStyles(List<MusicStyle> musicStyles) {
        this.musicStyles = musicStyles;
    }

    public String getSourceURI() {
        return sourceURI;
    }

    public void setSourceURI(String sourceURI) {
        this.sourceURI = sourceURI;
    }

    public List<MusicBrainz> getMusicBrainzs() {
        return musicBrainzs;
    }

    public void setMusicBrainzs(List<MusicBrainz> musicBrainzs) {
        this.musicBrainzs = musicBrainzs;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Artist{");
        sb.append("id=").append(id);
        sb.append(", displayName='").append(displayName).append('\'');
        sb.append(", website='").append(website).append('\'');
        sb.append(", summary='").append(summary).append('\'');
        sb.append(", musicStyles=").append(musicStyles);
        sb.append(", sourceURI='").append(sourceURI).append('\'');
        sb.append(", musicBrainzs=").append(musicBrainzs);
        sb.append('}');
        return sb.toString();
    }
}
