package nl.darkbyte.country_data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Currency(
    val country: String,
    val name: String,
    val code: String,
    val symbol: String = "",
)
