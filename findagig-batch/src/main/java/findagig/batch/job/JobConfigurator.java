package findagig.batch.job;

import findagig.batch.source.entity.Event;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfigurator {

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private EventReader eventReader;

    @Autowired
    private EventWriter eventWriter;

    @Autowired
    private EventProcessor eventProcessor;

    @Bean
    public Step gigsLoadStep() {
        return stepBuilderFactory.get("gigsLoadStep").<Event, Event>chunk(1)
                .reader(eventReader)
                .processor(eventProcessor)
                .writer(eventWriter).build();
    }

    @Bean
    public JobExecutionListener listener() {
        return new JobCompletionListener();
    }

}
