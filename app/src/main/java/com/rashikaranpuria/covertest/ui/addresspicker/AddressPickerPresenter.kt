package com.rashikaranpuria.covertest.ui.addresspicker

import com.rashikaranpuria.covertest.ui.base.BasePresenter
import javax.inject.Inject

class AddressPickerPresenter<V: IAddressPickerView> @Inject constructor(): BasePresenter<V>(), IAddressPickerPresenter<V> {
}