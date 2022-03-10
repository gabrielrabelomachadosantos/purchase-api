package com.gabrielsantos.purchaseapi.service.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielsantos.purchaseapi.dto.PurchaseDTO;
import com.gabrielsantos.purchaseapi.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Consumer {

    private final ObjectMapper objectMapper;

    private final PurchaseService purchaseService;

    @SneakyThrows
    @RabbitListener(queues = {"${spring.rabbitmq.queue.update-purchase-status}"})
    public void consumerUpdatePurchaseStatus(@Payload Message message) {
        PurchaseDTO purchaseDTO = objectMapper.readValue(message.getBody(), PurchaseDTO.class);

        purchaseService.updatePurchaseStatus(purchaseDTO);
    }

}
