package org.shop.zeptoorderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.kafka.autoconfigure.KafkaAutoConfiguration;

@SpringBootApplication(exclude = {KafkaAutoConfiguration.class})
public class ZeptoOrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZeptoOrderServiceApplication.class, args);
    }

}
