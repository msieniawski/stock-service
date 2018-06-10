package com.stockservice.bitpay

import com.stockservice.Receiver
import org.springframework.stereotype.Component

@Component
class BitpayReceiver : Receiver<Any> {
    override fun receiveMessage(message: Any) {

    }
}
