package findagig.source.entity;

public class Venue {
    private Long id;
    private String displayName;
    private String uri;
    private Float lng;
    private Float lat;
    private String location;
    private MetroArea metroArea;

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

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public MetroArea getMetroArea() {
        return metroArea;
    }

    public void setMetroArea(MetroArea metroArea) {
        this.metroArea = metroArea;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Venue{");
        sb.append("id=").append(id);
        sb.append(", displayName='").append(displayName).append('\'');
        sb.append(", uri='").append(uri).append('\'');
        sb.append(", lng=").append(lng);
        sb.append(", lat=").append(lat);
        sb.append(", location='").append(location).append('\'');
        sb.append(", metroArea=").append(metroArea);
        sb.append('}');
        return sb.toString();
    }

}
