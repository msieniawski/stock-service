package com.stockservice

import com.stockservice.proto.ExchangeRatesProto
import org.springframework.stereotype.Component

@Component
class Receiver {

    fun receiveMessage(message: ExchangeRatesProto.ExchangeRates) =
            println("Received: '$message'")
}