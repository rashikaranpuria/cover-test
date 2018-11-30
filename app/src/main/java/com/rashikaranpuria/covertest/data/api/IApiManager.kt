package com.rashikaranpuria.covertest.data.api

import com.rashikaranpuria.covertest.data.api.model.PlacesResponse.PlacesSearchResponse
import io.reactivex.Single

interface IApiManager {
    fun getAddressSuggestions(addressText: String): Single<PlacesSearchResponse>
}