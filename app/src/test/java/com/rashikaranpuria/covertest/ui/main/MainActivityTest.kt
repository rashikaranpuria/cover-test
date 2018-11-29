package com.rashikaranpuria.covertest.ui.main

import android.content.Intent
import com.rashikaranpuria.covertest.BuildConfig
import com.rashikaranpuria.covertest.FakeApplication
import com.rashikaranpuria.covertest.ui.addresspicker.AddressPickerActivity
import junit.framework.Assert.assertTrue
import kotlinx.android.synthetic.main.activity_main.*
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

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, application = FakeApplication::class, sdk = [21])
class MainActivityTest {

    @Mock
    lateinit var mainPresenter: MainPresenter<IMainActivityView>

    @InjectMocks
    lateinit var mainActivity: MainActivity

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        mainActivity = Robolectric.buildActivity(MainActivity::class.java).create().get()
        mainActivity.mainActivityPresenter = mainPresenter
    }

    @Test
    fun whenLaunchQuestionFLowClicked_thenPresenterLaunchQuestionFlowButtonClicked() {
        // when launch question flow button clicked
        mainActivity.launch_question_flow_button.performClick()
        // then presenter launchQuestionFlowButtonClicked called
        verify(mainPresenter).launchQuestionFlowButtonClicked()
    }

    @Test
    fun whenLaunchAddressPickerActivityCalled_thenAddressPickerActivityOpened() {
        // when launchAddressPickerActivity called
        mainActivity.launchAddressPickerActivity()
        // then Address picked activity launched
        val expectedIntent = Intent(mainActivity, AddressPickerActivity::class.java)
        val shadowActivity = Shadows.shadowOf(mainActivity)
        val actualIntent = shadowActivity.getNextStartedActivity()
        assertTrue(expectedIntent.filterEquals(actualIntent))
    }
}