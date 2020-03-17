package findagig.batch.domain.entity;

/**
 * Venus is an Entity/Bean class that represents a Venue on "Find a Gig" database
 *
 * @author Flavio A. Mandelli and Diego Irismar da Costa
 * @version 1.0
 */
public class Venue {

    private long id;
    private String displayName;
    private String description;
    private String website;
    private String street;
    private String zipCode;
    private Float latitude;
    private Float longitude;
    private Location location;
    private String sourceURI;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getSourceURI() {
        return sourceURI;
    }

    public void setSourceURI(String sourceURI) {
        this.sourceURI = sourceURI;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Venue{");
        sb.append("id=").append(id);
        sb.append(", displayName='").append(displayName).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", website='").append(website).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", zipCode='").append(zipCode).append('\'');
        sb.append(", latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append(", location=").append(location);
        sb.append(", sourceURI='").append(sourceURI).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
