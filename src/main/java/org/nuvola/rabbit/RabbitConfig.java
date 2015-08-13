package org.nuvola.rabbit;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    protected final String queueAvaya1 = "avaya1.queue";
    protected final String queueAvaya2 = "avaya2.queue";
    protected final String USER_KEY = "user.*";
    protected final String USER_KEY_TO = "user.to.*";
    protected final String NEW_USER_EXCHANGE = "NEW_USER_EXCHANGE";

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
        return new TopicExchange(NEW_USER_EXCHANGE);
    }

    @Bean
    public Binding queue1Binding() {
        return BindingBuilder.bind(avaya1Queue()).to(newUserExchange()).with(USER_KEY);
    }

    @Bean
    public Binding queue2Binding() {
        return BindingBuilder.bind(avaya2Queue()).to(newUserExchange()).with(USER_KEY);
    }

    @Bean
    public Binding queue2_ExcBinding() {
        return BindingBuilder.bind(avaya2Queue()).to(newUserExchange()).with(USER_KEY_TO);
    }
}
