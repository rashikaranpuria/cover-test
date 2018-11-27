package com.rashikaranpuria.covertest.di.component

import com.rashikaranpuria.covertest.di.module.AppModule
import com.rashikaranpuria.covertest.di.module.MainActivityModule
import com.rashikaranpuria.covertest.di.module.NetworkModule
import com.rashikaranpuria.covertest.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = [AppModule::class, NetworkModule::class])
interface AppComponent {

    fun mainActivityComponent(mainActivityModule: MainActivityModule): MainActivityComponent
}