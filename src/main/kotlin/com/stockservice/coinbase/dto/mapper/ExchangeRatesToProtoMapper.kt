package com.stockservice.coinbase.dto.mapper

import com.stockservice.coinbase.dto.exchangerates.ExchangeRatesDto
import com.stockservice.proto.ExchangeRatesProto.ExchangeRates
import com.stockservice.proto.ExchangeRatesProto.Rate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ExchangeRatesToProtoMapper : Mapper<ExchangeRatesDto, ExchangeRates> {

    @Value("\${coinbase.name}")
    private lateinit var stockExchangeName: String

    override fun map(from: ExchangeRatesDto): ExchangeRates =
            ExchangeRates.newBuilder()
                    .setStockExchangeName(stockExchangeName)
                    .setCurrency(from.data.currency)
                    .addAllRates(from.data.rates.map {
                        Rate.newBuilder()
                                .setCurrency(it.key)
                                .setPrice(it.value)
                                .build()
                    })
                    .build()
}