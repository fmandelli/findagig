package findagig.batch.api;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

    @Autowired
    private JobLauncher launcher;

    @Autowired
    private Job job;

    @RequestMapping("/start")
    public String startJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                .toJobParameters();

        launcher.run(job, jobParameters);

        return "Batch job has been invoked";
    }
}
