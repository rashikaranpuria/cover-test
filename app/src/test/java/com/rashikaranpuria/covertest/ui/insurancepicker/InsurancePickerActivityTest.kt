package com.rashikaranpuria.covertest.ui.insurancepicker

import android.content.Intent
import android.widget.ArrayAdapter
import com.rashikaranpuria.covertest.BuildConfig
import com.rashikaranpuria.covertest.FakeApplication
import com.rashikaranpuria.covertest.R
import com.rashikaranpuria.covertest.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_insurance_picker.*
import org.junit.Assert
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
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowAlertDialog

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, application = FakeApplication::class, sdk = [21])
class InsurancePickerActivityTest {

    @Mock
    lateinit var insurancePickerPresenter: IInsurancePickerPresenter<IInsurancePickerView>

    @Mock
    lateinit var insurancePickerAdapter: ArrayAdapter<String>

    @InjectMocks
    lateinit var insurancePickerActivity: InsurancePickerActivity

    lateinit var controller: ActivityController<InsurancePickerActivity>

    val dummyInsurerText = "Geico"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        controller = Robolectric.buildActivity(InsurancePickerActivity::class.java)
        insurancePickerActivity = controller.create().get()
        insurancePickerActivity.insurancePickerPresenter = insurancePickerPresenter
        insurancePickerActivity.insurancePickerAdapter = insurancePickerAdapter
    }

    @Test
    fun whenNextButtonClicked_thenPresenterNextbuttonClickedInvoked() {
        // when next button clicked
        insurancePickerActivity.insurer_autocomplete.setText(dummyInsurerText)
        insurancePickerActivity.next_button.performClick()
        // then presenter next button invoked
        verify(insurancePickerPresenter).nextButtonClicked(dummyInsurerText)
    }

    @Test
    fun whenShowNoOptionSelectedErrorCalled_thenErrorDialogShown() {
        // when show no option selected error called
        insurancePickerActivity.showNoOptionSelectedError()
        // verify no options selected dialog opens
        val alertDialog = ShadowAlertDialog.getLatestAlertDialog()
        val sAlert = Shadows.shadowOf(alertDialog)
        // alert dialog has correct title
        Assert.assertEquals(sAlert.title, insurancePickerActivity.resources.getString(R.string.no_option_error_title))
        Assert.assertEquals(sAlert.message, insurancePickerActivity.resources.getString(R.string.invalid_option_selected))
    }

    @Test
    fun whenLaunchMainActivityCalled_thenMainActivityLaunched() {
        // when launchInsurancePickerActivity called
        insurancePickerActivity.launchMainActivity()
        // then verify insurance picker activity opens
        val expectedIntent = Intent(insurancePickerActivity, MainActivity::class.java)
        val shadowActivity = Shadows.shadowOf(insurancePickerActivity)
        val actualIntent = shadowActivity.nextStartedActivity
        junit.framework.Assert.assertTrue(expectedIntent.filterEquals(actualIntent))
    }

    @Test
    fun whenActivityIsGoingToBeDestroyed_thenPresenterDetached() {
        // when activity on destroy
        controller.pause().stop().destroy()
        // then verify presenter detached
        verify(insurancePickerPresenter).onDetach()
    }
}