package com.batch.listeners;

import com.batch.dto.Payphone;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.StepExecutionListener;

public class GenericReaderListener implements ItemReadListener<Object>{
    @Override
    public void beforeRead() {

    }

    @Override
    public void afterRead(Object obj) {
        System.out.println(obj);
    }

    @Override
    public void onReadError(Exception e) {
        e.printStackTrace();
    }
}
