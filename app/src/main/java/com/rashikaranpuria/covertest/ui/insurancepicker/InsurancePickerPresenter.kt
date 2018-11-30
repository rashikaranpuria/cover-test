package com.rashikaranpuria.covertest.ui.insurancepicker

import com.rashikaranpuria.covertest.Constants
import com.rashikaranpuria.covertest.ui.base.BasePresenter
import javax.inject.Inject

class InsurancePickerPresenter<V : IInsurancePickerView> @Inject constructor() : BasePresenter<V>(), IInsurancePickerPresenter<V> {

    private fun isValidSelection(insurerText: String): Boolean = Constants.insurance_carriers.contains(insurerText)

    override fun nextButtonClicked(insurerText: String) {
        if (insurerText.isBlank() || insurerText.isEmpty() || !isValidSelection(insurerText)) {
            view?.showNoOptionSelectedError()
        } else {
            // ask activity to launch insurance picker activity with data from this activity
            view?.launchMainActivity()
        }
    }
}