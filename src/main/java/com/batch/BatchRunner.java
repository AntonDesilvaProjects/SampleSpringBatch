package com.batch;

import com.batch.dto.Payphone;
import com.batch.writer.PayphoneRawDataWriter;
import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BatchRunner {

    final static Logger logger = Logger.getLogger( BatchRunner.class );

    public static void main(String[] args) {
        String[] springCofig = {
                "spring/batch/config/context.xml",
                "spring/batch/jobs/payphone_job.xml"
        };
        ApplicationContext context = new ClassPathXmlApplicationContext(springCofig);
        JobLauncher launcher = (JobLauncher) context.getBean("jobLauncher");
        Job job = (Job) context.getBean("payphoneJob");

        try
        {
            logger.info("----------------Kicking off the batch!----------------------");
            JobExecution execution = launcher.run( job, new JobParameters() );
            logger.info("-------------------Batch Finished!-------------------");
        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
    }
}
