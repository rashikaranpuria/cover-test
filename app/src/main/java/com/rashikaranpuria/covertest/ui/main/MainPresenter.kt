package com.rashikaranpuria.covertest.ui.main

import com.rashikaranpuria.covertest.ui.base.BasePresenter
import javax.inject.Inject

class MainPresenter<V : IMainActivityView> @Inject constructor() : BasePresenter<V>(), IMainPresenter<V> {
    override fun launchQuestionFlowButtonClicked() {
        view?.launchAddressPickerActivity()
    }
}