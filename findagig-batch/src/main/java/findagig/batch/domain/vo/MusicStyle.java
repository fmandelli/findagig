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
    private final String displayName;

    public MusicStyle(int id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return displayName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MusicStyle{");
        sb.append("id=").append(id);
        sb.append(", displayName='").append(displayName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
