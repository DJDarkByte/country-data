package nl.darkbyte.country_data.model

import androidx.annotation.DrawableRes
import nl.darkbyte.country_data.World
import java.util.*

data class Country(
    val name: String,
    val alpha2: String,
    val alpha3: String,
    val continent: Continent,
    @DrawableRes
    val flagResource: Int,
) {
    val currency: Currency
        get() = World.getCurrencyForCountryCode(alpha2)

    val localizedName: String
        get() = Locale("", alpha2).displayCountry
}