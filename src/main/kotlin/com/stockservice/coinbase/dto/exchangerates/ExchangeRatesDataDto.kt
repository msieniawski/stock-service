package com.stockservice.coinbase.dto.exchangerates

data class ExchangeRatesDataDto(val currency: String, val rates: Map<String, String>)