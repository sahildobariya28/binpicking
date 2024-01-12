package com.scanner.binpicking.android

import android.app.Application
import com.scanner.binpicking.di.OrderItemModule
import com.scanner.binpicking.di.OrderModule
import com.scanner.binpicking.di.SinglePickingModule
import com.scanner.binpicking.di.initKoin
import com.scanner.binpicking.di.loginModule
import com.scanner.binpicking.di.platformUtilModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(
                platformUtilModule,
                loginModule,
                SinglePickingModule,
                OrderModule,
                OrderItemModule
            )
        }
    }
}