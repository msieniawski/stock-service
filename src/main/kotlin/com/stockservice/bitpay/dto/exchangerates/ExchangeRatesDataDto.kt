package com.stockservice.bitpay.dto.exchangerates

data class ExchangeRatesDataDto(
        val code: String,
        val name: String,
        val rate: String
)