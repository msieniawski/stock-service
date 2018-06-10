package com.stockservice.coinbase

import com.stockservice.coinbase.dto.mapper.CoinbaseExchangeRatesToProtoMapper
import com.stockservice.utils.logger
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class CoinbaseDataSender(
        private val coinbaseDataRequester: CoinbaseDataRequester,
        private val rabbitTemplate: RabbitTemplate,
        private val coinbaseExchangeRatesToProtoMapper: CoinbaseExchangeRatesToProtoMapper
) {
    private val log by logger()

    fun send() {
        val exchangeRates = coinbaseExchangeRatesToProtoMapper.map(coinbaseDataRequester.getExchangeRates("BTC"))
        rabbitTemplate.convertAndSend(exchangeRates)
        log.info("Message sent to '${rabbitTemplate.routingKey}'")
    }
}