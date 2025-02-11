package com.scanner.binpicking.presentation.screen.authentication.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthReq(
    val username: String,
    val password: String
)