package com.stockservice.configuration

import com.stockservice.RefreshDataReceiver
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer



@Configuration
class RabbitMqConfiguration {

    @Value("\${repository.queue.name}")
    private lateinit var repositoryQueueName: String

    @Value("\${trigger.queue.name}")
    private lateinit var triggerQueueName: String

    @Bean
    fun repositoryQueue() = Queue(repositoryQueueName, false)

    @Bean
    fun triggerQueue() = Queue(triggerQueueName, false)

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory) = RabbitTemplate(connectionFactory).apply {
        routingKey = repositoryQueueName
    }

    @Bean
    fun container(connFactory: ConnectionFactory, listenerAdapter: MessageListenerAdapter) = SimpleMessageListenerContainer().apply {
        connectionFactory = connFactory
        setQueueNames(triggerQueueName)
        setMessageListener(listenerAdapter)
    }

    @Bean
    fun listenerAdapter(receiver: RefreshDataReceiver) = MessageListenerAdapter(receiver, "receiveMessage")
}