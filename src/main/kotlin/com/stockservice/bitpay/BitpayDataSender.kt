package com.stockservice.bitpay

import com.stockservice.bitpay.dto.mapper.BitpayExchangeRatesToProtoMapper
import com.stockservice.utils.logger
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class BitpayDataSender(
        private val bitpayDataRequester: BitpayDataRequester,
        private val rabbitTemplate: RabbitTemplate,
        private val bitpayExchangeRatesToProtoMapper: BitpayExchangeRatesToProtoMapper
) {
    private val log by logger()

    @Scheduled(fixedDelay = 3_000)
    fun send() {
        val exchangeRates = bitpayExchangeRatesToProtoMapper.map(bitpayDataRequester.getExchangeRates("BTC"))
        rabbitTemplate.convertAndSend(exchangeRates)
        log.info("Message sent to '${rabbitTemplate.routingKey}'")
    }
}