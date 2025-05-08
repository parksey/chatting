package org.chat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SseApplication

fun main(args: Array<String>) {
    runApplication<SseApplication>(*args)
}