package nl.darkbyte.country_data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostalCodeValidation(
    val country: String,
    val regex: String? = null
)
