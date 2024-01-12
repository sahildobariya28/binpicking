package com.scanner.binpicking.utils

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toKotlinLocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
actual fun localDateTime(str: String): LocalDateTime {

    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm", Locale.ENGLISH)
    return java.time.LocalDateTime
        .parse(str, formatter)
        .toKotlinLocalDateTime()
}