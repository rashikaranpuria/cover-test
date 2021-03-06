package com.rashikaranpuria.covertest.di.component

import com.rashikaranpuria.covertest.di.module.MainActivityModule
import com.rashikaranpuria.covertest.di.module.AddressPickerModule
import com.rashikaranpuria.covertest.di.module.InsurancePickerModule
import com.rashikaranpuria.covertest.di.module.AppModule
import com.rashikaranpuria.covertest.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {

    fun mainActivityComponent(mainActivityModule: MainActivityModule): MainActivityComponent
    fun addressPickerComponent(addressPickerModule: AddressPickerModule): AddressPickerComponent
    fun insurancePickerComponent(insurancePickerModule: InsurancePickerModule): InsurancePickerComponent
}