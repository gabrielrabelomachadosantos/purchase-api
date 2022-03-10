package com.gabrielsantos.purchaseapi.service.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielsantos.purchaseapi.dto.PurchaseDTO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Producer {

    private final RabbitTemplate rabbitTemplate;

    @Value(value = "${spring.rabbitmq.queue.registered-purchase}")
    private String registeredPurchaseQueue;

    @Value(value = "${spring.rabbitmq.queue.approved-purchase}")
    private String approvedPurchaseQueue;

    @Value(value = "${spring.rabbitmq.queue.reproved-purchase}")
    private String reprovedPurchaseQueue;

    private final ObjectMapper objectMapper;

    @SneakyThrows
    public void sendToRegisteredPurchaseQueue(PurchaseDTO purchaseDTO) {
        rabbitTemplate.convertAndSend(registeredPurchaseQueue, objectMapper.writeValueAsString(purchaseDTO));
    }

    @SneakyThrows
    public void sendToApprovedPurchaseQueue(PurchaseDTO purchaseDTO) {
        rabbitTemplate.convertAndSend(approvedPurchaseQueue, objectMapper.writeValueAsString(purchaseDTO));
    }

    @SneakyThrows
    public void sendToReprovedPurchaseQueue(PurchaseDTO purchaseDTO) {
        rabbitTemplate.convertAndSend(reprovedPurchaseQueue, objectMapper.writeValueAsString(purchaseDTO));
    }

}
