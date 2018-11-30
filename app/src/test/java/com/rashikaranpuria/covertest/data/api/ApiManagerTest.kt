package com.rashikaranpuria.covertest.data.api

import org.junit.Before

import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class ApiManagerTest {

    @Mock
    lateinit var placesApi: PlacesApi

    @InjectMocks
    lateinit var apiManager: ApiManager

    var dummyString = "123 Boule"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun whenGetAddressSuggestionsCalled_thenPlacesApiGetAddressSuggestionsCalled() {
        // when getAddressSuggestions called
        apiManager.getAddressSuggestions(dummyString)
        // then places api getAddressSuggestions called
        verify(placesApi).getAddressSuggestions(dummyString)
    }
}