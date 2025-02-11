package com.scanner.binpicking.util

import platform.AudioToolbox.AudioServicesPlaySystemSound
import platform.AudioToolbox.kSystemSoundID_Vibrate

actual fun beepSound() {
    // System sound ID for the beep sound
    AudioServicesPlaySystemSound(kSystemSoundID_Vibrate) // You can also use other sound IDs if needed
}