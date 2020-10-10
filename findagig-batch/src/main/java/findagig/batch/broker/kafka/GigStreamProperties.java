package findagig.batch.broker.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author diego.costa on 04.10.20
 **/

@Configuration
public class GigStreamProperties implements StreamProperties {

    @Value("${kafka.cluster.brokers}")
    private List<String> brokers = new ArrayList<>();

    @Value("${kafka.stream.topic.gig}")
    private String topic;

    @Value("${kafka.stream.gig.app.id}")
    private String appId;

    @Value("${kafka.stream.gig.client.id}")
    private String clientId;

    @Override
    public String getTopic() {
        return topic;
    }

    @Override
    public String getToTopic() {
        return null;
    }

    @Override
    public String getAppId() {
        return appId;
    }

    @Override
    public String getClientId() {
        return clientId;
    }

    public List<String> getBrokers() {
        return brokers;
    }
}
