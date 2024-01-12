package com.scanner.binpicking.utils

import androidx.compose.ui.geometry.Size
import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import platform.AVFAudio.AVAudioSession
import platform.AVFAudio.AVAudioSessionCategoryPlayback
import platform.AVFAudio.setActive
import platform.AVFoundation.AVPlayer
import platform.AVFoundation.AVPlayerItem
import platform.AVFoundation.AVPlayerItem.Companion.playerItemWithURL
import platform.AVFoundation.currentItem
import platform.AVFoundation.pause
import platform.AVFoundation.play
import platform.AVFoundation.replaceCurrentItemWithPlayerItem
import platform.AVFoundation.seekToTime
import platform.Foundation.NSBundle
import platform.Foundation.NSURL


actual class SoundMediaPlayer : KoinComponent {
    private var audioPlayer: AVPlayer? = null

    @OptIn(ExperimentalForeignApi::class)
    actual suspend fun playSound(actionType: ActionType) {


        val soundFileName = when (actionType) {
            ActionType.SUCCESS -> "success"
            ActionType.ERROR -> "error"
        }

        val soundFilePath =
            NSBundle.mainBundle.pathForResource("raw/$soundFileName", ofType = "mp3")

        soundFilePath?.let {
            val soundFileUrl = NSURL.fileURLWithPath(it)

//            val audioSession = AVAudioSession.sharedInstance()
//            audioSession.setCategory(AVAudioSessionCategoryPlayback, error = null)
//            audioSession.setActive(true, error = null)
            val audioSession = AVAudioSession.sharedInstance()
            try {
                audioSession.setCategory(AVAudioSessionCategoryPlayback, error = null)
                try {
                    audioSession.setActive(true, error = null)
                } catch (e: Exception) {
                    println("djsklfjwerwwww:  000  ${e.message}")
                }
                // Handle errors
            } catch (e: Exception) {
                println("djsklfjwerwwww:  111  ${e.message}")
            }

            audioPlayer?.pause()
            audioPlayer = AVPlayer.playerWithURL(soundFileUrl)
            audioPlayer!!.play()
            delay(1500)

        }
    }
}

