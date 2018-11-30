package com.rashikaranpuria.covertest.ui.addresspicker

import com.rashikaranpuria.covertest.ImmediateSchedulerRule
import com.rashikaranpuria.covertest.R
import com.rashikaranpuria.covertest.data.DataManager
import com.rashikaranpuria.covertest.data.api.model.PlacesResponse.PlacesSearchResponse
import com.rashikaranpuria.covertest.data.api.model.PlacesResponse.PredictionsItem
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.Mockito.`when` as _when
import org.mockito.MockitoAnnotations
import java.io.IOException

class AddressPickerPresenterTest {

    @get:Rule
    var immediateSchedulerRule = ImmediateSchedulerRule()

    @Mock
    lateinit var addressPickerActivity: IAddressPickerView

    @Mock
    lateinit var mCompositeDisposable: CompositeDisposable

    @Mock
    lateinit var mDataManager: DataManager

    @Mock
    lateinit var dummyPredictionsItem: PredictionsItem
    val dummyAddressText = "123 Boule"
    @Mock
    lateinit var predictions: PlacesSearchResponse

    @InjectMocks
    lateinit var addressPickerPresenter: AddressPickerPresenter<IAddressPickerView>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        addressPickerPresenter.onAttach(addressPickerActivity)
        _when(mDataManager.getAddressSuggestions(dummyAddressText)).thenReturn(Single.just(predictions))
    }

    @Test
    fun whenNotifyTextChangedCalled_thenRemoveSelectedItemCalled() {
        // when notifyTextChanged called
        addressPickerPresenter.notifyTextChanged()
        // then removeSelectedItem called on view
        verify(addressPickerActivity).removeSelectedItem()
    }

    @Test
    fun whenAddressPickerItemClickedCalled_thenViewSuggestionsClearedAndViewSelectedItemShowed() {
        // when addressPickerItemClicked called
        addressPickerPresenter.addressPickerItemClicked(dummyPredictionsItem)
        // then view clearCurrentSuggestions and view removeSelectedItem called
        verify(addressPickerActivity).clearCurrentSuggestions()
        verify(addressPickerActivity).showSelectedItem()
    }

    @Test
    fun whenPresenterDetachedFromView_thenCompositeDisposableIsDisposed() {
        // when onDetach called
        addressPickerPresenter.onDetach()
        // then composite disposable is disposed
        verify(mCompositeDisposable).dispose()
    }

    @Test
    fun verifyIsValidSelectionReturnsFalse_whenSelectedItemIsNull() {
        // when view.selectedItem is null
        _when(addressPickerActivity.selectedItem).thenReturn(null)
        // then is valid selection gives false
        assertEquals(false, addressPickerPresenter.isValidSelection())
    }

    @Test
    fun verifyIsValidSelectionReturnsTrue_whenSelectedItemIsNotNull() {
        // when view.selectedItem is not null
        _when(addressPickerActivity.selectedItem).thenReturn(dummyPredictionsItem)
        // then is valid selection gives true
        assertEquals(true, addressPickerPresenter.isValidSelection())
    }

    @Test
    fun whenNotifyDelayedDebouncedTextChangeCalled_thenDataManagerGetAddressSuggestionsCalled() {
        // when notifyDelayedDebouncedTextChange called
        addressPickerPresenter.notifyDelayedDebouncedTextChange(dummyAddressText)
        // then verify data manager get address suggestions for address text called
        verify(mDataManager).getAddressSuggestions(dummyAddressText)
    }

    @Test
    fun whenDataManagerGetAddressSuggestionsCallSuccessful_thenViewSetAddressSuggestionsInAdapterCalled() {
        // when data manager get address suggestions successful
        _when(mDataManager.getAddressSuggestions(dummyAddressText)).thenReturn(Single.just(predictions))
        addressPickerPresenter.notifyDelayedDebouncedTextChange(dummyAddressText)
        // then verify view set suggestions in adapter called
        verify(addressPickerActivity).setAddressSuggestionInAdapter(predictions.predictions)
    }

    @Test
    fun whenDataManagerGetAddressSuggestionsCallUnsuccessful_thenViewshowErrorCalled() {
        // when data manager get address suggestions successful
        _when(mDataManager.getAddressSuggestions(dummyAddressText)).thenReturn(Single.error(IOException("")))
        addressPickerPresenter.notifyDelayedDebouncedTextChange(dummyAddressText)
        // then verify view set suggestions in adapter called
        verify(addressPickerActivity).showError(anyInt(), anyString())
    }

    @Test
    fun whenNextButtonClickedAndIsValidSelection_thenLaunchInsuranceActivityCalled() {
        // when next button clicked and is valid selection
        _when(addressPickerActivity.selectedItem).thenReturn(dummyPredictionsItem)
        addressPickerPresenter.nextButtonClicked(dummyAddressText)
        // then launch insurance activity called
        verify(addressPickerActivity).launchInsurancePickerActivity()
    }

    @Test
    fun whenNextButtonClickedAndIsNotValidSelection_thenShowNoOptionSelectedErrorCalled() {
        // when next button clicked and is valid selection
        _when(addressPickerActivity.selectedItem).thenReturn(null)
        addressPickerPresenter.nextButtonClicked(dummyAddressText)
        // then show no option selected called on view
        verify(addressPickerActivity).showNoOptionSelectedError()
    }
}