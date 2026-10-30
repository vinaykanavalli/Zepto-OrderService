package org.shop.zeptoorderservice.Service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    @Autowired(required = false)
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String message) {
        if (kafkaTemplate != null) {
            kafkaTemplate.send(topic, message);
        } else {
            System.out.println("Kafka is disabled; message not sent: " + message);
        }
    }
}