package com.rashikaranpuria.covertest.data.api.model.PlacesResponse

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class PlacesSearchResponse(

    @field:SerializedName("predictions")
    val predictions: List<PredictionsItem>,

    @field:SerializedName("status")
    val status: String? = null
)