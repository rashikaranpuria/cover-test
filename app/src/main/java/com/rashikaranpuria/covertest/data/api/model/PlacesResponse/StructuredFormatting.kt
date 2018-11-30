package com.rashikaranpuria.covertest.data.api.model.PlacesResponse

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class StructuredFormatting(

    @field:SerializedName("main_text_matched_substrings")
    val mainTextMatchedSubstrings: List<MainTextMatchedSubstringsItem?>? = null,

    @field:SerializedName("secondary_text")
    val secondaryText: String? = null,

    @field:SerializedName("main_text")
    val mainText: String? = null
)