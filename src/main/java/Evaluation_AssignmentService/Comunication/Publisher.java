package Evaluation_AssignmentService.Comunication;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
public class Publisher {
    private final RabbitTemplate rabbitTemplate;
    private final Queue comunQueue;
    public Publisher(RabbitTemplate rabbitTemplate, Queue comunQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.comunQueue = comunQueue;
    }

    public void sendMessageComunQueue(final String message){
        rabbitTemplate.convertAndSend(comunQueue.getName(),message);
    }
}
