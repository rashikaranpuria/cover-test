package com.rashikaranpuria.covertest.di.module

import android.content.Context
import android.widget.ArrayAdapter
import com.rashikaranpuria.covertest.Constants
import com.rashikaranpuria.covertest.ui.insurancepicker.IInsurancePickerPresenter
import com.rashikaranpuria.covertest.ui.insurancepicker.IInsurancePickerView
import com.rashikaranpuria.covertest.ui.insurancepicker.InsurancePickerPresenter
import dagger.Module
import dagger.Provides

@Module
class InsurancePickerModule(val context: Context) {

    @Provides
    fun providesInsurancePickerPresenter(insurancePickerPresenter: InsurancePickerPresenter<IInsurancePickerView>) = insurancePickerPresenter as IInsurancePickerPresenter<IInsurancePickerView>

    @Provides
    fun providesInsurancePickerAdapter() = ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, Constants.insurance_carriers)
}