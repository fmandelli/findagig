package findagig.batch.domain.entity;

import findagig.batch.domain.vo.MusicStyle;

import java.util.List;

/**
 * Artist is a Entity/Bean class that represents an Artist on "Find a Gig" database
 *
 * @author Flavio A. Mandelli and Diego Irismar da Costa
 * @version 1.0
 */
public class Artist {

    private long id;
    private String name;
    private String website;
    private String summary;
    private List<MusicStyle> musicStyles;
    private String sourceURI;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
