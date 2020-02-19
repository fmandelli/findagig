package findagig.batch.domain.vo;

/**
 * Country class (Value Object) represents a country.
 * Ex: Canada, German, Brazil, and etc
 *
 * @author Flavio A. Mandelli and Diego Irismar da Costa
 * @version 1.0
 */
public class Country {

    private final int id;
    private final String displayName;

    public Country(int id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return displayName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Country{");
        sb.append("id=").append(id);
        sb.append(", displayName='").append(displayName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
