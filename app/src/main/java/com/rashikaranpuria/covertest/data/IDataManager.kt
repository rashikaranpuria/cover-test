package com.rashikaranpuria.covertest.data

import com.rashikaranpuria.covertest.data.api.model.PlacesResponse.PlacesSearchResponse
import io.reactivex.Single

interface IDataManager {
    fun getAddressSuggestions(addressText: String): Single<PlacesSearchResponse>
}