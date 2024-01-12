package com.scanner.binpicking.core

sealed class ImageUiState {
    class Success(val data: List<Any>) : ImageUiState()
    class Error(val error: String) : ImageUiState()
    object Loading : ImageUiState()
}