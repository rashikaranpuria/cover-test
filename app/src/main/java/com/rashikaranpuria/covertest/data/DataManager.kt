package com.rashikaranpuria.covertest.data

import com.rashikaranpuria.covertest.data.api.ApiManager
import com.rashikaranpuria.covertest.data.api.model.PlacesResponse.PlacesSearchResponse
import io.reactivex.Single
import javax.inject.Inject

class DataManager @Inject constructor(val apiManager: ApiManager) : IDataManager {
    override fun getAddressSuggestions(addressText: String): Single<PlacesSearchResponse> =
            apiManager.getAddressSuggestions(addressText)
}