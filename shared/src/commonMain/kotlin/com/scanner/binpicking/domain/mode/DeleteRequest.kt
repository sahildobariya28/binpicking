package com.scanner.binpicking.domain.mode


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeleteRequest(
    @SerialName("id")
    val id: List<Int>
)