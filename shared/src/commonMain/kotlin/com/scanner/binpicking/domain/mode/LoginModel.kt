package com.scanner.binpicking.domain.mode

import kotlinx.serialization.Serializable

@Serializable
data class LoginModel(
    val username: String,
    val password: String
)