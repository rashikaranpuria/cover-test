package com.rashikaranpuria.covertest.data.api

import com.rashikaranpuria.covertest.data.api.model.PlacesResponse.PlacesSearchResponse
import io.reactivex.Single
import javax.inject.Inject

class ApiManager @Inject constructor(val placesApi: PlacesApi) : IApiManager {
    override fun getAddressSuggestions(addressText: String): Single<PlacesSearchResponse> =
            placesApi.getAddressSuggestions(addressText)
}