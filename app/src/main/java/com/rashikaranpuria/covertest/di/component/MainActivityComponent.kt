package com.rashikaranpuria.covertest.di.component

import com.rashikaranpuria.covertest.di.module.MainActivityModule
import com.rashikaranpuria.covertest.ui.main.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [MainActivityModule::class])
interface MainActivityComponent {
    fun inject(mainActivity: MainActivity)
}