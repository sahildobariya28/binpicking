package com.scanner.binpicking

import android.app.Application
import com.scanner.binpicking.di.platformUtilModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    companion object {
        var instance: BaseApplication? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(
                platformUtilModule,
//                loginModule,
//                PickingModule,
//                OrderModule,
//                OrderItemModule
            )
        }
    }
}

