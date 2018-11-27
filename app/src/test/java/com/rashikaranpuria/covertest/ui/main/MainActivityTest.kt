package com.rashikaranpuria.covertest.ui.main

import com.rashikaranpuria.covertest.BuildConfig
import com.rashikaranpuria.covertest.FakeApplication
import com.rashikaranpuria.covertest.ui.addresspicker.AddressPickerActivity
import junit.framework.Assert.assertEquals
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowActivity
import android.content.Intent
import junit.framework.Assert.assertTrue
import org.robolectric.Shadows






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
    fun whenLaunchQuestionFLowClicked_thenAddressPickerActivityOpened() {
        // when launch question flow button clicked
        mainActivity.launch_question_flow_button.performClick()
        // then Address picked activity launched
        val expectedIntent = Intent(mainActivity, AddressPickerActivity::class.java)
        val shadowActivity = Shadows.shadowOf(mainActivity)
        val actualIntent = shadowActivity.getNextStartedActivity()
        assertTrue(expectedIntent.filterEquals(actualIntent))
    }
}