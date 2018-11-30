package com.rashikaranpuria.covertest.ui.addresspicker

import com.rashikaranpuria.covertest.data.api.model.PlacesResponse.PredictionsItem
import com.rashikaranpuria.covertest.ui.base.IBaseView

interface IAddressPickerView : IBaseView {
    var selectedItem: PredictionsItem?

    fun launchInsurancePickerActivity()
    fun initClickListeners()
    fun initAddressPickerAdapter()
    fun setAddressSuggestionInAdapter(predictions: List<PredictionsItem>)
    fun showNoOptionSelectedError()
    fun clearCurrentSuggestions()
    fun removeSelectedItem()
    fun showSelectedItem()
}