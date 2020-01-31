package findagig.batch.job;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.io.Serializable;
import java.util.Collections;
import java.util.Vector;

public class GigsReader implements ItemReader<String> {

    private Vector<String> strings = new Vector<>();

    public GigsReader() {
        this.strings.add("Value 1");
        this.strings.add("Value 2");
    }

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (!strings.isEmpty())
            return strings.remove(0);
        return null;
    }
}
