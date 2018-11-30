package com.rashikaranpuria.covertest.data

import com.rashikaranpuria.covertest.data.api.ApiManager
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class DataManagerTest {

    @Mock
    lateinit var apiManager: ApiManager

    @InjectMocks
    lateinit var dataManager: DataManager
    var dummyString = "123 Boule"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun whenGetAddressSuggestionsCalled_thenApiManagerGetAddressSuggestionsCalled() {
        // when get address suggestions called
        dataManager.getAddressSuggestions(dummyString)
        // then api manager get address suggestions called
        verify(apiManager).getAddressSuggestions(dummyString)
    }
}