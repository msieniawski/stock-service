package com.stockservice

import com.stockservice.bitpay.BitpayDataSender
import com.stockservice.coinbase.CoinbaseDataSender
import com.stockservice.utils.logger
import org.springframework.stereotype.Component

@Component
class RefreshDataReceiver(
        private val bitpayDataSender: BitpayDataSender,
        private val coinbaseDataSender: CoinbaseDataSender
) : Receiver<Any> {
    private val log by logger()

    override fun receiveMessage(message: Any) {
        log.info("Received message from Currency Rates Agent, updating currency rates...")
        bitpayDataSender.send()
        coinbaseDataSender.send()
    }
}