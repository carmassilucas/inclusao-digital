package br.com.ifsp.aluno.inclusaodigital.producer;

import br.com.ifsp.aluno.inclusaodigital.module.chat.entity.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    @Value(value = "${broker.queue.message.name}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishMessage(Message message) {
        rabbitTemplate.convertAndSend("", routingKey, message);
    }
}
