package com.example.authors.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

@EnableJms
@Configuration
public class JMSConfig {
    @Bean
    public Queue queueAuthors(){
        return new ActiveMQQueue("authors.queue");
    }

    @Bean
    public Queue queueAuthor(){
        return new ActiveMQQueue("author.queue");
    }
}
