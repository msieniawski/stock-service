package com.stockservice

interface Mapper<in From, out To> {
    fun map(from: From): To
}