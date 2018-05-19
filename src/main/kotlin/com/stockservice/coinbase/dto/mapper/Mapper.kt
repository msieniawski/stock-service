package com.stockservice.coinbase.dto.mapper

interface Mapper<in From, out To> {

    fun map(from: From): To

    fun map(from: Collection<From>): Collection<To> =
            from.map { map(it) }
}