package findagig.batch.domain.vo;

/**
 * MusicStyle class (Value Object) represents a music style.
 * Ex: punk, alternative, heavy metal, and etc
 *
 * @author Flavio A. Mandelli and Diego Irismar da Costa
 * @version 1.0
 */
public class MusicStyle {

    private final int id;
    private final String description;

    public MusicStyle(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
