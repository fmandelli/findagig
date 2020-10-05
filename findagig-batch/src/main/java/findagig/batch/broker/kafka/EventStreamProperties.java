package findagig.batch.broker.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author diego.costa on 04.10.20
 **/

@Configuration
public class EventStreamProperties implements StreamProperties{

    @Value("${kafka.cluster.brokers}")
    private List<String> brokers = new ArrayList<>();

    @Value("${kafka.stream.topic.event}")
    private String topic;

    @Value("${kafka.stream.topic.gig}")
    private String toTopic;

    @Value("${kafka.stream.event.app.id}")
    private String appId;

    @Value("${kafka.stream.event.client.id}")
    private String clientId;

    public String getTopic() {
        return topic;
    }

    @Override
    public String getToTopic() {
        return toTopic;
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
