package com.rashikaranpuria.covertest.ui.insurancepicker

import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class InsurancePickerPresenterTest {

    @Mock
    lateinit var insurancePickerActivity: IInsurancePickerView

    @InjectMocks
    lateinit var insurancePickerPresenter: InsurancePickerPresenter<IInsurancePickerView>

    val validInsurer = "Geico"
    val blankInsurer = ""
    val invalidInsurer = "Z223YX1313WV"

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        insurancePickerPresenter.onAttach(insurancePickerActivity)
    }

    @Test
    fun whenNextButtonClickedInvokedWithValidInsurerText_thenLaunchMainActivityCalled() {
        // when next button clicked called with insurer text: "$validInsurer"
        insurancePickerPresenter.nextButtonClicked(validInsurer)
        // then check if launch main activity called
        verify(insurancePickerActivity).launchMainActivity()
    }

    @Test
    fun whenNextButtonClickedInvokedWithBlankInsurerText_thenShowNoOptionSelectedErrorCalled() {
        // when next button clicked called with blank insurer text
        insurancePickerPresenter.nextButtonClicked(blankInsurer)
        // then check if show no option selected error called
        verify(insurancePickerActivity).showNoOptionSelectedError()
    }

    @Test
    fun whenNextButtonClickedInvokedWithInValidInsurerText_thenShowNoOptionSelectedErrorCalled() {
        // when next button clicked called with blank insurer text
        insurancePickerPresenter.nextButtonClicked(invalidInsurer)
        // then check if show no option selected error called
        verify(insurancePickerActivity).showNoOptionSelectedError()
    }
}