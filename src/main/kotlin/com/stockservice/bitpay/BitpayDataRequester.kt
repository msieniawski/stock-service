package com.stockservice.bitpay

import com.stockservice.bitpay.dto.exchangerates.ExchangeRatesDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class BitpayDataRequester {
    @Value("\${bitpay.api.url}")
    private lateinit var apiUrl: String

    private val restTemplate = RestTemplate()

    fun getExchangeRates(currency: String): ExchangeRatesDto = restTemplate
            .getForEntity("$apiUrl/rates/$currency", ExchangeRatesDto::class.java)
            .body!!
}