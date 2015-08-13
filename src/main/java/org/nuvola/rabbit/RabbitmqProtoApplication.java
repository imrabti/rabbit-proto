package org.nuvola.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitmqProtoApplication implements CommandLineRunner {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProtoApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        rabbitTemplate.convertAndSend("NEW_USER_EXCHANGE", "user.*", "Hello there this is the first topic stuff...");
        rabbitTemplate.convertAndSend("NEW_USER_EXCHANGE", "user.to.*", "this exclusive");
    }
}
