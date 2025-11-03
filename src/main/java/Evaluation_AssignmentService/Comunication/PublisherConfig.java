package Evaluation_AssignmentService.Comunication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublisherConfig {

    @Value("${comunQueue.name}")
    private String comunQueue;

    @Bean
    public org.springframework.amqp.core.Queue nameQueue() {return new org.springframework.amqp.core.Queue(comunQueue,true);}
}
