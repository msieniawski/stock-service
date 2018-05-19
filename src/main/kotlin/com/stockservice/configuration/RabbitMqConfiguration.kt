package com.stockservice.configuration

import com.stockservice.Receiver
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer

@Configuration
class RabbitMqConfiguration {

    @Value("\${rabbitmq.queue.name}")
    private lateinit var queueName: String

    @Value("\${rabbitmq.topicExchange.name}")
    private lateinit var topicExchangeName: String

    @Bean
    fun queue() = Queue(queueName, false)

    @Bean
    fun exchange() = TopicExchange(topicExchangeName)

    @Bean
    fun binding(queue: Queue, exchange: TopicExchange): Binding =
            BindingBuilder
                    .bind(queue)
                    .to(exchange)
                    .with("*")

    @Bean
    fun listenerAdapter(receiver: Receiver) = MessageListenerAdapter(receiver, "receiveMessage")

    @Bean
    fun container(connFactory: ConnectionFactory, listenerAdapter: MessageListenerAdapter) =
            SimpleMessageListenerContainer().apply {
                connectionFactory = connFactory
                //messageListener = listenerAdapter
                setQueueNames(queueName)
            }
}