package findagig.source.entity;

public class State {

    private String displayName;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("State{");
        sb.append("displayName='").append(displayName).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
