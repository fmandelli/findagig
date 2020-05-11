package findagig.batch.job;

import findagig.source.entity.Event;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfigurator {


    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private EventReader eventReader;

    @Autowired
    private EventWriter eventWriter;

    @Bean
    public Job processJob() {
        return jobBuilderFactory.get("processJob")
                .incrementer(new RunIdIncrementer()).listener(listener())
                .flow(orderStep1()).end().build();
    }

    @Bean
    public Step orderStep1() {
        return stepBuilderFactory.get("orderStep1").<Event, Event>chunk(1)
        //return stepBuilderFactory.get("orderStep1").<Event, List<Gig>>chunk(1)
                .reader(eventReader)
                //.processor(new GigsProcessor())
                .writer(eventWriter).build();
    }

    @Bean
    public JobExecutionListener listener() {
        return new JobCompletionListener();
    }

}
