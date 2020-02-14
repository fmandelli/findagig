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
    private String name;
    private Country country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
