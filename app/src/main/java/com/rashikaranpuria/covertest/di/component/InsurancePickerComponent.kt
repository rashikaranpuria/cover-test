package com.rashikaranpuria.covertest.di.component

import com.rashikaranpuria.covertest.di.module.InsurancePickerModule
import com.rashikaranpuria.covertest.ui.insurancepicker.InsurancePickerActivity
import dagger.Subcomponent

@Subcomponent(modules = [InsurancePickerModule::class])
interface InsurancePickerComponent {
    fun inject(insurancePickerActivity: InsurancePickerActivity)
}