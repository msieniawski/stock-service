package com.stockservice.coinbase

import com.stockservice.Receiver
import org.springframework.stereotype.Component

@Component
class CoinbaseReceiver : Receiver<Any> {
    override fun receiveMessage(message: Any) {

    }
}