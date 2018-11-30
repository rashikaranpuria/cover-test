package com.rashikaranpuria.covertest.ui.addresspicker

import com.rashikaranpuria.covertest.R
import com.rashikaranpuria.covertest.data.DataManager
import com.rashikaranpuria.covertest.data.api.model.PlacesResponse.PredictionsItem
import com.rashikaranpuria.covertest.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddressPickerPresenter<V : IAddressPickerView> @Inject constructor(val mCompositeDisposable: CompositeDisposable, val mDataManager: DataManager) : BasePresenter<V>(), IAddressPickerPresenter<V> {

    override fun nextButtonClicked(addressText: String) {
        if (!isValidSelection()) {
            view?.showNoOptionSelectedError()
        } else {
//             ask activity to launch insurance picker activity with data from this activity
            view?.launchInsurancePickerActivity()
        }
    }

    override fun notifyDelayedDebouncedTextChange(addressText: String) {
        // fetch suggestions from api based on current address text
        mCompositeDisposable.add(
            mDataManager.getAddressSuggestions(addressText)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        view?.setAddressSuggestionInAdapter(it.predictions)
                    },
                    onError = {
                        view?.showError(R.string.get_suggestions_failed, it.localizedMessage)
                    }
                )
        )
    }

    override fun notifyTextChanged() {
        // unset selected value
        view?.removeSelectedItem()
    }

    override fun addressPickerItemClicked(selectedItem: PredictionsItem) {
        view?.selectedItem = selectedItem
        // clear suggestions
        view?.clearCurrentSuggestions()
        // set selected value
        view?.showSelectedItem()
    }

    fun isValidSelection() = view?.selectedItem != null

    override fun onDetach() {
        super.onDetach()
        mCompositeDisposable.dispose()
    }
}