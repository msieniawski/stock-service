package com.stockservice.utils

import org.slf4j.LoggerFactory

fun <T : Any> T.logger() = lazy { LoggerFactory.getLogger(javaClass) }