package findagig.batch.source.entity;

public class Venue {
    private Long id;
    private String displayName;
    private String uri;
    private String street;
    private String zip;
    private Float lng;
    private Float lat;
    private String phone;
    private String website;
    private String capacity;
    private String description;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        sb.append(", street='").append(street).append('\'');
        sb.append(", zip='").append(zip).append('\'');
        sb.append(", lng=").append(lng);
        sb.append(", lat=").append(lat);
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", website='").append(website).append('\'');
        sb.append(", capacity='").append(capacity).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", metroArea=").append(metroArea);
        sb.append('}');
        return sb.toString();
    }
}
