package com.rashikaranpuria.covertest.di.module

import com.rashikaranpuria.covertest.data.DataManager
import com.rashikaranpuria.covertest.data.IDataManager
import com.rashikaranpuria.covertest.data.api.ApiManager
import com.rashikaranpuria.covertest.data.api.IApiManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideDataManager(mDataManager: DataManager): IDataManager = mDataManager

    @Provides
    @Singleton
    fun provideApiManager(mApiManager: ApiManager) = mApiManager as IApiManager
}