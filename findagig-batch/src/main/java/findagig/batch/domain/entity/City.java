package findagig.batch.domain.entity;

import findagig.batch.domain.vo.Country;

/**
 * City is an Entity/Bean class that represents a city.
 * Ex: Winnipeg, Toronto, Buenos Aires, and etc
 *
 * @author Flavio A. Mandelli and Diego Irismar da Costa
 * @version 1.0
 */
public class City {

    private int id;
    private String displayName;
    private Country country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("City{");
        sb.append("id=").append(id);
        sb.append(", displayName='").append(displayName).append('\'');
        sb.append(", country=").append(country);
        sb.append('}');
        return sb.toString();
    }
}
