package com.scanner.binpicking.utils

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toKotlinInstant
import kotlinx.datetime.toLocalDateTime
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale

actual fun localDateTime(str: String): LocalDateTime {
    val formatter = NSDateFormatter().apply {
        dateFormat = "dd/MM/yyyy - HH:mm"
        locale = NSLocale.currentLocale
    }
    return formatter
        .dateFromString(str)
        // extensions functions provided by kotlinx.datetime
        ?.toKotlinInstant()
        ?.toLocalDateTime(TimeZone.currentSystemDefault())
        ?: throw IllegalStateException("Failed to convert String $str to LocalDateTime")
}