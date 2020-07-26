package findagig.batch.api;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

    @Autowired
    private JobLauncher launcher;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private JobExecutionListener listener;

    @Autowired
    private Step step;

    @RequestMapping("/start")
    public String startJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                .toJobParameters();

        Job job = jobBuilderFactory.get("processJob")
                .incrementer(new RunIdIncrementer()).listener(listener)
                .flow(step).end().build();

        launcher.run(job, jobParameters);
        return "Batch job has been invoked";
    }
}
