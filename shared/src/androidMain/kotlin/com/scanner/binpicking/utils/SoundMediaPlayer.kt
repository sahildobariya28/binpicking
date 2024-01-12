package com.scanner.binpicking.utils

import android.content.Context
import android.media.MediaPlayer
import com.scanner.binpicking.R
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.UUID


actual class SoundMediaPlayer : KoinComponent {
    private val context: Context by inject()
    private var player: MediaPlayer? = null

    actual suspend fun playSound(actionType: ActionType) {
        player?.release()

        player = when (actionType) {
            ActionType.SUCCESS -> MediaPlayer.create(context, R.raw.success)
            ActionType.ERROR -> MediaPlayer.create(context, R.raw.error)
        }
        player?.start()
    }
}