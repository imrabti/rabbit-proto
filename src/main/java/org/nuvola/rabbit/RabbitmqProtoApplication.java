package org.nuvola.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class RabbitmqProtoApplication implements CommandLineRunner {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${nuvola.url}")
    private String url;

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProtoApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        rabbitTemplate.convertAndSend("NEW_USER_EXCHANGE", "user.*", "Hello there this is the first topic stuff...");
        rabbitTemplate.convertAndSend("NEW_USER_EXCHANGE", "user.to.*", "this exclusive");
    }
}
