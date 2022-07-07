package team.odds.mentor.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import team.odds.mentor.service.QueueReceiverService;

@Configuration
@EnableRabbit
public class MessageQueuingConfig {

    @Bean
    public TopicExchange topic() {
        return new TopicExchange("odds.mentor");
    }

    @Bean
    public Queue queue() {
        return new Queue("odds-mentor-message", false);
    }

    @Bean
    public Binding binding(TopicExchange topic, Queue queue) {
        return BindingBuilder.bind(queue).to(topic).with("odds.mentor.#");
    }

    @Bean
    MessageListenerAdapter listenerAdapter(QueueReceiverService receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                                    MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("odds-mentor-message");
        container.setMessageListener(listenerAdapter);
        return container;
    }
}
