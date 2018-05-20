package com.stockservice.bitpay.dto.mapper

import com.stockservice.Mapper
import com.stockservice.bitpay.dto.exchangerates.ExchangeRatesDto
import com.stockservice.proto.ExchangeRatesProto.ExchangeRates
import com.stockservice.proto.ExchangeRatesProto.Rate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class BitpayExchangeRatesToProtoMapper : Mapper<ExchangeRatesDto, ExchangeRates> {

    @Value("\${bitpay.name}")
    private lateinit var stockExchangeName: String

    override fun map(from: ExchangeRatesDto): ExchangeRates = ExchangeRates.newBuilder()
            .setStockExchangeName(stockExchangeName)
            .setCurrency(from.data.first { it.rate == "1" }.code)
            .addAllRates(from.data.map {
                Rate.newBuilder()
                        .setCurrency(it.code)
                        .setPrice(it.rate)
                        .build()
            })
            .build()
}