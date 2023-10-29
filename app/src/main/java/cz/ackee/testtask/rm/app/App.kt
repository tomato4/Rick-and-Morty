package cz.ackee.testtask.rm.app

import android.app.Application
import cz.ackee.testtask.rm.di.Module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        startKoin {
            androidContext(this@App)
            modules(
                Module.listModule
            )
        }
    }
}