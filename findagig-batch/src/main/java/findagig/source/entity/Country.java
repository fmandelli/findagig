package findagig.source.entity;

public class Country {

    private String displayName;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Country{");
        sb.append("displayName='").append(displayName).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
