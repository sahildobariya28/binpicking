package com.scanner.binpicking.util


enum class ActionType {
    SUCCESS,
    ERROR
}

expect class SoundMediaPlayer() {
    suspend fun playSound(actionType: ActionType)
}