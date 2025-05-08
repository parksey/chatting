package org.chat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PollingApplication

fun main(args: Array<String>) {
    runApplication<PollingApplication>(*args)
}