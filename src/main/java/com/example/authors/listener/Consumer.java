package com.example.authors.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @JmsListener(destination = "authors.queue")
    public void listener (String author){
        System.out.println("Received authors = " + author);
    }
}
