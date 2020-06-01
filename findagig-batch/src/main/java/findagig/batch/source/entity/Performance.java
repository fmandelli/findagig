package findagig.batch.source.entity;

public class Performance {

    private Long id;
    private Artist artist;
    private String displayName;

    /* Where this performance features on the event billing (Starts at 1) */
    private Integer billingIndex;
    private Billing billing;

    /* The lineup billing of this performance */
    public enum Billing {
        HEADLINE, SUPPORT
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getBillingIndex() {
        return billingIndex;
    }

    public void setBillingIndex(Integer billingIndex) {
        this.billingIndex = billingIndex;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Performance{");
        sb.append("id=").append(id);
        sb.append(", artist=").append(artist);
        sb.append(", displayName='").append(displayName).append('\'');
        sb.append(", billingIndex=").append(billingIndex);
        sb.append(", billing=").append(billing);
        sb.append('}');
        return sb.toString();
    }
}
