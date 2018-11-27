package com.rashikaranpuria.covertest.di.module

import com.rashikaranpuria.covertest.ui.main.IMainActivityView
import com.rashikaranpuria.covertest.ui.main.IMainPresenter
import com.rashikaranpuria.covertest.ui.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun provideMainActivityPresenter(mainActivityPresenter: MainPresenter<IMainActivityView>) = mainActivityPresenter as IMainPresenter<IMainActivityView>
}