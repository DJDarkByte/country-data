package nl.darkbyte.country_data

import android.content.Context
import androidx.annotation.DrawableRes
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import nl.darkbyte.country_data.exception.CountryDataException
import nl.darkbyte.country_data.model.Continent
import nl.darkbyte.country_data.model.Country
import nl.darkbyte.country_data.model.Currency
import nl.darkbyte.country_data.model.PostalCodeValidation
import nl.darkbyte.country_data.moshi.LocalCountryAdapterFactory

object World {
    private lateinit var currencyList: Map<String, Currency>
    private lateinit var countryList: List<Country>
    private lateinit var postalCodeValidationList: Map<String, PostalCodeValidation>

    private val universe = Country(
        "World",
        "xx",
        "xxx",
        Continent.UNIVERSE,
        R.drawable.flag_globe
    )

    internal fun init(context: Context) {
        val moshi = setupMoshi(context)
        currencyList = parseCurrencyList(context, moshi).associateBy { it.country }
        postalCodeValidationList = parsePostalCodesValidationList(context, moshi).associateBy { it.country }
        countryList = parseCountryList(context, moshi).sortedBy { it.name }
    }

    private fun setupMoshi(context: Context): Moshi {
        return Moshi.Builder()
            .add(LocalCountryAdapterFactory(context))
            .build()
    }

    private fun parseCurrencyList(context: Context, moshi: Moshi): List<Currency> {
        val currencyListType = Types.newParameterizedType(List::class.java, Currency::class.java)
        return AssetsReader.readFromAssets(context, R.raw.currencies)?.let { json ->
            moshi.adapter<List<Currency>>(currencyListType).fromJson(json)
        } ?: emptyList()
    }

    private fun parseCountryList(context: Context, moshi: Moshi): List<Country> {
        val countryListType = Types.newParameterizedType(List::class.java, Country::class.java)
        return AssetsReader.readFromAssets(context, R.raw.countries)?.let { json ->
            moshi.adapter<List<Country>>(countryListType).fromJson(json)
        } ?: emptyList()
    }

    private fun parsePostalCodesValidationList(context: Context, moshi: Moshi): List<PostalCodeValidation> {
        val postalCodeValidationListType = Types.newParameterizedType(List::class.java, PostalCodeValidation::class.java)
        return AssetsReader.readFromAssets(context, R.raw.postal_codes)?.let { json ->
            moshi.adapter<List<PostalCodeValidation>>(postalCodeValidationListType).fromJson(json)
        } ?: emptyList()
    }

    fun getAllCountries(): List<Country> {
        checkIsInitialized()
        return countryList
    }

    fun getAllCurrencies(): List<Currency> {
        checkIsInitialized()
        return currencyList.values.toList()
    }

    fun getCountryFrom(code: String): Country {
        checkIsInitialized()
        return countryList.firstOrNull {
            it.alpha2.equals(code, true) || it.alpha3.equals(
                code,
                true
            )
        } ?: universe
    }

    fun getCountriesOfContinent(continent: Continent): List<Country> {
        checkIsInitialized()
        return countryList.filter { it.continent == continent }
    }

    fun getCurrencyForCountryCode(code: String): Currency {
        checkIsInitialized()
        return currencyList[code] ?: currencyList.getValue("xx")
    }

    fun getPostalCodeRegexOf(code: String): String {
        checkIsInitialized()
        return postalCodeValidationList[code]?.regex ?: ".*"
    }

    @DrawableRes
    fun getFlagOf(code: String): Int {
        checkIsInitialized()
        return getCountryFrom(code).flagResource
    }

    @Throws(CountryDataException::class)
    private fun checkIsInitialized() {
        if (!this::currencyList.isInitialized || !this::countryList.isInitialized) {
            throw CountryDataException(
                "You have to call World.init(getApplicationContext()) before this method."
            )
        }
    }
}