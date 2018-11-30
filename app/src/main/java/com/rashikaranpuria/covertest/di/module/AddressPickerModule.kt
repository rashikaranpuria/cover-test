package com.rashikaranpuria.covertest.di.module

import android.content.Context
import com.rashikaranpuria.covertest.ui.addresspicker.AddressPickerAdapter
import com.rashikaranpuria.covertest.ui.addresspicker.AddressPickerPresenter
import com.rashikaranpuria.covertest.ui.addresspicker.IAddressPickerPresenter
import com.rashikaranpuria.covertest.ui.addresspicker.IAddressPickerView
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class AddressPickerModule(val context: Context) {

    @Provides
    fun providesAddressPickerPresenter(addressPickerPresenter: AddressPickerPresenter<IAddressPickerView>) = addressPickerPresenter as IAddressPickerPresenter<IAddressPickerView>

    @Provides
    fun providesAddressPickerAdapter() = AddressPickerAdapter()

    @Provides
    fun providesCompositeDisposable() = CompositeDisposable()
}