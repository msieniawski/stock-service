package com.stockservice

interface StockExchangeService {

    fun getCurrencies(): Collection<String>

    fun getExchangeRates(currency: String): Map<String, String>
}