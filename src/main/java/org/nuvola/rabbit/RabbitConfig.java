package org.nuvola.rabbit;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Value("${nuvola.avaya.queueAcd1}")
    private String queueAvaya1;
    @Value("${nuvola.avaya.queueAcd2}")
    private String queueAvaya2;
    @Value("${nuvola.avaya.key.userKey}")
    private String userKey;
    @Value("${nuvola.avaya.key.userToKey}")
    private String userKeyTo;
    @Value("${nuvola.avaya.exchange.topicUser}")
    private String topicUser;

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public Queue avaya1Queue() {
        return new Queue(this.queueAvaya1);
    }

    @Bean
    public Queue avaya2Queue() {
        return new Queue(this.queueAvaya2);
    }

    @Bean
    public TopicExchange newUserExchange() {
        return new TopicExchange(topicUser);
    }

    @Bean
    public Binding queue1Binding() {
        return BindingBuilder.bind(avaya1Queue()).to(newUserExchange()).with(userKey);
    }

    @Bean
    public Binding queue2Binding() {
        return BindingBuilder.bind(avaya2Queue()).to(newUserExchange()).with(userKey);
    }

    @Bean
    public Binding queue2_ExcBinding() {
        return BindingBuilder.bind(avaya2Queue()).to(newUserExchange()).with(userKeyTo);
    }
}
