package com.rashikaranpuria.covertest.ui.main

import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MainPresenterTest {

    @Mock
    lateinit var mainActivity: IMainActivityView

    @InjectMocks
    lateinit var mainPresenter: MainPresenter<IMainActivityView>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mainPresenter.onAttach(mainActivity)
    }

    @Test
    fun whenLaunchQuestionFlowButtonClicked_ThenLaunchAddressPickerActivityCalled() {
        // when launchQuestionFlowButtonClicked
        mainPresenter.launchQuestionFlowButtonClicked()
        // then launchAddressPickerActivity called
        verify(mainActivity).launchAddressPickerActivity()
    }
}