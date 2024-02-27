package ru.unlim1x.myflowersclassic.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.unlim1x.myflowersclassic.di.appModule
import ru.unlim1x.myflowersclassic.di.dataModule
import ru.unlim1x.myflowersclassic.di.domainModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(appModule, domainModule, dataModule))
        }

    }
}