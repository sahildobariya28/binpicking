package com.scanner.binpicking.utils


enum class ActionType {
    SUCCESS,
    ERROR
}

expect class SoundMediaPlayer() {
    suspend fun playSound(actionType: ActionType)
}