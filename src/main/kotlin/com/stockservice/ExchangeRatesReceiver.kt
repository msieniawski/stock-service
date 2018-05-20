package com.stockservice

import com.stockservice.proto.ExchangeRatesProto
import com.stockservice.utils.logger
import org.springframework.stereotype.Component

@Component
class ExchangeRatesReceiver : Receiver<ExchangeRatesProto.ExchangeRates> {
    private val log by logger()

    override fun receiveMessage(message: ExchangeRatesProto.ExchangeRates) =
            log.info("Received: '${message.ratesList.size}'")
}