package com.rashikaranpuria.covertest.data.api

import com.rashikaranpuria.covertest.BuildConfig
import com.rashikaranpuria.covertest.data.api.model.PlacesResponse.PlacesSearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesApi {

    @GET("autocomplete/json?types=address&key=${BuildConfig.PLACES_API_KEY}")
    fun getAddressSuggestions(@Query("input") addressText: String): Single<PlacesSearchResponse>
}