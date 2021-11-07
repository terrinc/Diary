package com.example.diary.core.application

import android.app.Application
import com.example.diary.BuildConfig
import com.example.diary.core.di.appModule
import com.example.diary.core.di.dataModule
import com.example.diary.core.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class DiaryApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidContext(this@DiaryApp)
            modules(listOf(appModule, dataModule, domainModule) )
        }
    }
}
