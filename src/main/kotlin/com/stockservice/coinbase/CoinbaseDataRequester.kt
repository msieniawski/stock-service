package com.stockservice.coinbase

import com.stockservice.coinbase.dto.currencies.CurrenciesDto
import com.stockservice.coinbase.dto.exchangerates.ExchangeRatesDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class CoinbaseDataRequester {
    @Value("\${coinbase.api.url}")
    private lateinit var apiUrl: String

    private val restTemplate = RestTemplate()

    fun getCurrencies(): CurrenciesDto = restTemplate
            .getForEntity("$apiUrl/currencies", CurrenciesDto::class.java)
            .body!!

    fun getExchangeRates(currency: String): ExchangeRatesDto = restTemplate
            .getForEntity("$apiUrl/exchange-rates?currency=$currency", ExchangeRatesDto::class.java)
            .body!!
}