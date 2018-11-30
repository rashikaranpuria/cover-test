package com.rashikaranpuria.covertest.ui.addresspicker

import android.content.Intent
import com.rashikaranpuria.covertest.BuildConfig
import com.rashikaranpuria.covertest.FakeApplication
import com.rashikaranpuria.covertest.R
import com.rashikaranpuria.covertest.data.api.model.PlacesResponse.PredictionsItem
import com.rashikaranpuria.covertest.data.api.model.PlacesResponse.StructuredFormatting
import com.rashikaranpuria.covertest.ui.insurancepicker.InsurancePickerActivity
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.android.synthetic.main.activity_address_picker.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowAlertDialog
import org.robolectric.android.controller.ActivityController
import rx.Subscription

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, application = FakeApplication::class, sdk = [21])
class AddressPickerActivityTest {

    @Mock
    lateinit var addressPickerPresenter: AddressPickerPresenter<IAddressPickerView>

    @Mock
    lateinit var addressPickerAdapter: AddressPickerAdapter

    @Mock
    lateinit var addressPickerTextChangeSubscription: Subscription
    @Mock
    lateinit var nextButtonClickSubscription: Subscription

    @InjectMocks
    lateinit var addressPickerActivity: AddressPickerActivity

    @Mock
    lateinit var predictions: List<PredictionsItem>

    lateinit var controller: ActivityController<AddressPickerActivity>

    val dummyText = "123 Boule"
    val dummyText1 = "San Francisco, CA"
    @Mock
    lateinit var dummyPrediction: PredictionsItem

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        controller = Robolectric.buildActivity(AddressPickerActivity::class.java)
        addressPickerActivity = controller.create().get()
        addressPickerActivity.addressPickerPresenter = addressPickerPresenter
        addressPickerActivity.addressPickerAdapter = addressPickerAdapter
        addressPickerActivity.addressPickerTextChangeSubscription = addressPickerTextChangeSubscription
        addressPickerActivity.nextButtonClickSubscription = nextButtonClickSubscription
    }

    @Test
    fun whenSetAddressSuggestionInAdapterCalled_thenSuggestionsSetInAdapter() {
        // when address picked set suggestions called
        addressPickerActivity.setAddressSuggestionInAdapter(predictions)
        // then adapter items updated with suggested predictions
        verify(addressPickerAdapter).items = predictions
    }

//    @Test
//    fun whenAddressAutocompleteTextChangesDebounced_thenPresenterNotifiedOfDataChangedDelayed() {
//        // when address picker text changed
//        addressPickerActivity.address_autocomplete.setText(dummyText)
//        // then presenter notified of text change DEBOUNCED!!!
//        verify(addressPickerPresenter).notifyDelayedDebouncedTextChange(dummyText)
//    }

    @Test
    fun whenAddressAutocompleteTextChanges_thenPresenterNotifiedOfDataChanged() {
        // when address picker text changed
        addressPickerActivity.address_autocomplete.setText(dummyText)
        // then presenter notified of text change
        verify(addressPickerPresenter).notifyTextChanged()
    }

    @Test
    fun whenNextButtonClicked_thePresenterNextButtonClickedCalled() {
        // when next button clicked
        addressPickerActivity.address_autocomplete.setText(dummyText)
        addressPickerActivity.next_button.performClick()
        // then presenter next button clicked invoked with text as address autocomplete edit text
        verify(addressPickerPresenter).nextButtonClicked(dummyText)
    }

    @Test
    fun whenClearSuggestionsCalled_thenAdapterItemsCleared() {
        // when clear suggestions called
        addressPickerActivity.clearCurrentSuggestions()
        // then adapter items set as empty
        verify(addressPickerAdapter).items = listOf()
    }

    @Test
    fun whenShowSelectedItemCalled_thenAddressSelectionTextViewUpdatedWithSelectedItem() {
        // when selected item set and activity show selected item invoked
        addressPickerActivity.selectedItem = PredictionsItem(
            structuredFormatting = StructuredFormatting(
                mainText = dummyText, secondaryText = dummyText1), id = "1")
        addressPickerActivity.showSelectedItem()
        // then address selection
        assertEquals(dummyText, addressPickerActivity.address_selection_text1.text.toString())
        assertEquals(dummyText1, addressPickerActivity.address_selection_text2.text.toString())
    }

    @Test
    fun whenRemoveSelectedItemCalled_thenSelectedItemClearedAndAddressSelectionTextViewCleared() {
        // when activity selected item called
        addressPickerActivity.removeSelectedItem()
        // then verify that selected item is un set and selected item textview is cleared
        assertEquals(addressPickerActivity.selectedItem, null)
        assertTrue(addressPickerActivity.address_selection_text1.text.isEmpty())
        assertTrue(addressPickerActivity.address_selection_text2.text.isEmpty())
    }

    @Test
    fun whenLaunchInsurancePickerActivityCalled_thenInsurancePickerActivityOpens() {
        // when launchInsurancePickerActivity called
        addressPickerActivity.launchInsurancePickerActivity()
        // then verify insurance picker activity opens
        val expectedIntent = Intent(addressPickerActivity, InsurancePickerActivity::class.java)
        val shadowActivity = Shadows.shadowOf(addressPickerActivity)
        val actualIntent = shadowActivity.nextStartedActivity
        Assert.assertTrue(expectedIntent.filterEquals(actualIntent))
    }

    @Test
    fun whenShowNoOptionSelectedErrorCalled_thenErrorDialogShown() {
        // when show no option selected error called
        addressPickerActivity.showNoOptionSelectedError()
        // verify no options selected dialog opens
        val alertDialog = ShadowAlertDialog.getLatestAlertDialog()
        val sAlert = Shadows.shadowOf(alertDialog)
        // alert dialog has correct title
        assertEquals(sAlert.title, addressPickerActivity.resources.getString(R.string.no_option_error_title))
        assertEquals(sAlert.message, addressPickerActivity.resources.getString(R.string.invalid_option_selected))
    }

    @Test
    fun whenActivityIsGoingToBeDestroyed_thenPresenterDetached() {
        // when activity on destroy
        controller.pause().stop().destroy()
        // then verify presenter detached
        verify(addressPickerPresenter).onDetach()
        // also all subscriptions unsubscribed
        verify(addressPickerTextChangeSubscription).unsubscribe()
        verify(nextButtonClickSubscription).unsubscribe()
    }

    @Test
    fun whenAddressItemClickListenerOnClickInvoked_thenPresentPickerItemClickedCalled() {
        // when Address Item Click Listener OnClick Invoked
        addressPickerActivity.addressItemClickListener.onItemClicked(dummyPrediction)
        // then presenter addressPickerItemClicked called
        verify(addressPickerPresenter).addressPickerItemClicked(dummyPrediction)
    }
}