package com.stockservice.coinbase

import com.stockservice.coinbase.dto.mapper.ExchangeRatesToProtoMapper
import com.stockservice.utils.logger
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class CoinbaseDataSender(
        private val coinbaseDataRequester: CoinbaseDataRequester,
        private val rabbitTemplate: RabbitTemplate,
        private val exchangeRatesToProtoMapper: ExchangeRatesToProtoMapper
) {
    private val log by logger()

    @Scheduled(fixedDelay = 3_000)
    fun send() {
        val exchangeRates = exchangeRatesToProtoMapper.map(coinbaseDataRequester.getExchangeRates("BTC"))
        rabbitTemplate.convertAndSend(exchangeRates)
        log.info("Message sent to '${rabbitTemplate.routingKey}'")
    }
}