package com.stockservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.PropertySource
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
@PropertySource("classpath:exchange.properties")
class StockServiceApplication

fun main(args: Array<String>) {
    runApplication<StockServiceApplication>(*args)
}
