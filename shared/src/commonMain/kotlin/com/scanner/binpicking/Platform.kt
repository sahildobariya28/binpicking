package com.scanner.binpicking

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform