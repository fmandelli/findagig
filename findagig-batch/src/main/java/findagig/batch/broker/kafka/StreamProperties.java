package findagig.batch.broker.kafka;

import java.util.List;

/**
 * @author diego.costa on 04.10.20
 **/

public interface StreamProperties {

    String getTopic();

    String getToTopic();

    String getAppId();

    String getClientId();

    List<String> getBrokers();
}
