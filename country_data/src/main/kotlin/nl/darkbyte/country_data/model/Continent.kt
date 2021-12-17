package nl.darkbyte.country_data.model

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
enum class Continent(val id: String, val title: String) {
    @Json(name = "AF")
    AFRICA("AF", "Africa"),

    @Json(name = "AS")
    ASIA("AS", "Asia"),

    @Json(name = "NA")
    NORTH_AMERICA("NA", "North America"),

    @Json(name = "SA")
    SOUTH_AMERICA("SA", "South America"),

    @Json(name = "OC")
    OCEANIA("OC", "Oceania"),

    @Json(name = "EU")
    EUROPE("EU", "Europe"),

    @Json(name = "AN")
    ANTARCTICA("AN", "Antarctica"),

    @Json(name = "UNX")
    UNIVERSE("UNX", "Universe")
}