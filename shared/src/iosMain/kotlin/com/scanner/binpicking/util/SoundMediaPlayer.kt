package com.scanner.binpicking.util

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.delay
import platform.AVFAudio.AVAudioSession
import platform.AVFAudio.AVAudioSessionCategoryPlayback
import platform.AVFAudio.setActive
import platform.AVFoundation.AVPlayer
import platform.AVFoundation.pause
import platform.AVFoundation.play
import platform.Foundation.NSBundle
import platform.Foundation.NSURL


actual class SoundMediaPlayer {
    private var audioPlayer: AVPlayer? = null

    @OptIn(ExperimentalForeignApi::class)
    actual suspend fun playSound(actionType: ActionType) {


        val soundFileName = when (actionType) {
            ActionType.SUCCESS -> "success"
            ActionType.ERROR -> "error"
        }

        val soundFilePath = NSBundle.mainBundle.pathForResource("raw/$soundFileName", ofType = "mp3")

        soundFilePath?.let {
            val soundFileUrl = NSURL.fileURLWithPath(it)

//            val audioSession = AVAudioSession.sharedInstance()
//            audioSession.setCategory(AVAudioSessionCategoryPlayback, error = null)
//            audioSession.setActive(true, error = null)
            val audioSession = AVAudioSession.sharedInstance()
            try {
                audioSession.setCategory(AVAudioSessionCategoryPlayback, error = null)
                audioSession.setActive(true, error = null)
            } catch (_: Exception) {
            }

            audioPlayer?.pause()
            audioPlayer = AVPlayer.playerWithURL(soundFileUrl)
            audioPlayer!!.play()
            delay(1500)

        }
    }
}

