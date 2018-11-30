package com.rashikaranpuria.covertest.di.component

import com.rashikaranpuria.covertest.di.module.AddressPickerModule
import com.rashikaranpuria.covertest.ui.addresspicker.AddressPickerActivity
import dagger.Subcomponent

@Subcomponent(modules = [AddressPickerModule::class])
interface AddressPickerComponent {
    fun inject(addressPickerActivity: AddressPickerActivity)
}