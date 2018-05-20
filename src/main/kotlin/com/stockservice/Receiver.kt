package com.stockservice

interface Receiver<T> {
    fun receiveMessage(message: T)
}