package org.chat.core.util

import com.querydsl.core.types.dsl.BooleanExpression
import java.util.*
import java.util.function.Function

fun <T> generate(value: T, function: Function<T, BooleanExpression?>): BooleanExpression? {
    if (Objects.isNull(value)) {
        return null
    }

    if (value is String && value.isBlank()) {
        return null
    }

    return function.apply(value)
}