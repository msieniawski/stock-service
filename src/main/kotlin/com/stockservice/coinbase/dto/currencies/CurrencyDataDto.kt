package com.stockservice.coinbase.dto.currencies

import com.fasterxml.jackson.annotation.JsonProperty

data class CurrencyDataDto(
        val id: String,
        val name: String,
        @JsonProperty("min_size")
        val minSize: String
)