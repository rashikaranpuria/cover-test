package com.rashikaranpuria.covertest.ui.insurancepicker

import com.rashikaranpuria.covertest.ui.base.IBasePresenter

interface IInsurancePickerPresenter<V : IInsurancePickerView> : IBasePresenter<V> {

    fun nextButtonClicked(insurerText: String)
}