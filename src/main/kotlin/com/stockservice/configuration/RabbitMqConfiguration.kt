package com.stockservice.configuration

import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMqConfiguration {

    @Value("\${rabbitmq.queue.name}")
    private lateinit var queueName: String

    @Bean
    fun queue() = Queue(queueName, false)

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory) = RabbitTemplate(connectionFactory).apply {
        routingKey = queueName
    }
}