package com.batch.listeners;

import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

public class GenericWriterListener implements ItemWriteListener<Object> {

    @Override
    public void beforeWrite(List<?> list) {
        System.out.println("Before writing " + list.size() );
    }

    @Override
    public void afterWrite(List<?> list) {
        System.out.println("After writing " + list.size() );
        for(Object o : list )
            System.out.print( o.toString() );
        System.out.println("----------------------------------");
    }

    @Override
    public void onWriteError(Exception e, List<?> list) {

    }
}
