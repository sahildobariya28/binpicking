package com.scanner.binpicking.di

import com.scanner.binpicking.util.SoundMediaPlayer
import org.koin.dsl.module

val platformUtilModule = module {
    single { SoundMediaPlayer() }
}