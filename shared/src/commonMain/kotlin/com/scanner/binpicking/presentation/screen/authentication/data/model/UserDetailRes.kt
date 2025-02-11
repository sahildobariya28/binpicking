package com.scanner.binpicking.presentation.screen.authentication.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDetailRes(
    @SerialName("role")
    val role: String,
    @SerialName("user_id")
    val userId: String,
    @SerialName("username")
    val username: String,
    @SerialName("name")
    val name: String,
    @SerialName("email")
    val email: String,
    var accessToken: String = ""
)