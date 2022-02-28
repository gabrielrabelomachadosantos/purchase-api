package com.gabrielsantos.purchaseapi.service.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielsantos.purchaseapi.model.Purchase;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Producer {

    private final RabbitTemplate rabbitTemplate;

    @Value(value = "${spring.rabbitmq.queue.name}")
    private Queue queue;

    private final ObjectMapper objectMapper;

    @PostMapping
    @SneakyThrows
    public void sendToQueue(Purchase purchase) {
        rabbitTemplate.convertAndSend(queue.getName(), objectMapper.writeValueAsString(purchase));
    }

}
