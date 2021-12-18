package nl.darkbyte.country_data.moshi

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import nl.darkbyte.country_data.World
import nl.darkbyte.country_data.model.Country

class CountryAdapter {
    @FromJson
    fun fromJson(countryCode: String): Country {
        return World.getCountryFrom(countryCode)
    }

    @ToJson
    fun toJson(country: Country): String {
        return country.alpha2.lowercase()
    }
}