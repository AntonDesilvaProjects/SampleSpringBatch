package com.batch.listeners;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class GenericStepListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println( "beforeStep" + stepExecution.getStepName() );
    }
    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println( "afterStep" + stepExecution.getStepName() );
        return null;
    }
}
