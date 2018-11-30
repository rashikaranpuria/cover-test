package com.rashikaranpuria.covertest

import android.app.Application
import com.rashikaranpuria.covertest.di.component.AppComponent
import com.rashikaranpuria.covertest.di.component.DaggerAppComponent
import com.rashikaranpuria.covertest.di.module.AppModule

open class CoverApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule())
            .build()
    }
}