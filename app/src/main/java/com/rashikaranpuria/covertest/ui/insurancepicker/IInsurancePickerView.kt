package com.rashikaranpuria.covertest.ui.insurancepicker

import com.rashikaranpuria.covertest.ui.base.IBaseView

interface IInsurancePickerView : IBaseView {
    fun initAdapter()
    fun initClickListeners()
    fun showNoOptionSelectedError()
    fun launchMainActivity()
}