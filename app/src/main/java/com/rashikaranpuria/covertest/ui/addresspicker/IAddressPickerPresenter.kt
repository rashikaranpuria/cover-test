package com.rashikaranpuria.covertest.ui.addresspicker

import com.rashikaranpuria.covertest.data.api.model.PlacesResponse.PredictionsItem
import com.rashikaranpuria.covertest.ui.base.IBasePresenter

interface IAddressPickerPresenter<V : IAddressPickerView> : IBasePresenter<V> {
    fun nextButtonClicked(addressText: String)
    fun addressPickerItemClicked(selectedItem: PredictionsItem)
    fun notifyTextChanged()
    fun notifyDelayedDebouncedTextChange(addressText: String)
}