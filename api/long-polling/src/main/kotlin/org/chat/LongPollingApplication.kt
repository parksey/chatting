package org.chat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LongPollingApplication

fun main(args: Array<String>) {
    runApplication<LongPollingApplication>(*args)
}