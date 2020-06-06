package findagig.batch.source.entity;

public class MetroArea {

    private Long id;
    private String uri;
    private String displayName;
    private Country country;
    private State state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MetroArea{");
        sb.append("id=").append(id);
        sb.append(", uri='").append(uri).append('\'');
        sb.append(", displayName='").append(displayName).append('\'');
        sb.append(", country=").append(country);
        sb.append(", state=").append(state);
        sb.append('}');
        return sb.toString();
    }
}
