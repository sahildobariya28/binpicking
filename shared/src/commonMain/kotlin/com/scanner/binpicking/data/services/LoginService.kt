package com.scanner.binpicking.data.services

import io.ktor.http.HttpStatusCode

interface LoginService {
    suspend fun getLogin(username: String, password: String): Pair<String, HttpStatusCode?>
    suspend fun getPickerId(accessToken: String): Pair<String, HttpStatusCode?>
}