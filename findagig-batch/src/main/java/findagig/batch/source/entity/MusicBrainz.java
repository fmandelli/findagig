package findagig.batch.source.entity;

public class MusicBrainz {

    private String mbid;
    private String href;


    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MusicBrainz{");
        sb.append("mbid='").append(mbid).append('\'');
        sb.append(", href='").append(href).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
