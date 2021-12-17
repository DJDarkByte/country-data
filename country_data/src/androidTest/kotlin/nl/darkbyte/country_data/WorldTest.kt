package nl.darkbyte.country_data

import androidx.test.platform.app.InstrumentationRegistry
import nl.darkbyte.country_data.model.Continent
import nl.darkbyte.country_data.model.Country
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class WorldTest {
    private val tag = "WorldTest"

    private fun log(message: String) {
        println("$tag: $message")
    }

    @Before
    fun setUp() {
        World.init(InstrumentationRegistry.getInstrumentation().context)
    }

    @Test
    fun getFlagOf() {
        val flagFromISO2: Int = World.getFlagOf("se")
        val flagFromISO3: Int = World.getFlagOf("swe")
        Assert.assertTrue(flagFromISO2 != -1)
        Assert.assertTrue(flagFromISO3 != -1)
        Assert.assertEquals(flagFromISO2, flagFromISO3)
    }

    @Test
    fun getCountryFrom() {
        val sweden2 = World.getCountryFrom("se")
        val sweden3 = World.getCountryFrom("swe")
        Assert.assertEquals(sweden2, sweden3)
    }

    @Test
    fun getAllCountries() {
        val countries: List<Country> = World.getAllCountries()
        Assert.assertTrue(countries.isNotEmpty())
        for (country in countries) {
            Assert.assertNotNull("Country " + country.name + "'s currency is not null",
                    country.currency)
            Assert.assertNotNull("A country has a name", country.name)
        }

        log("Country list size= " + countries.size)
    }

    @Test
    fun getAllCurrencies() {
        val currencies = World.getAllCurrencies()
        Assert.assertFalse("The list of currencies is not empty", currencies.isEmpty())
        for (currency in currencies) {
            val countryName = World.getCountryFrom(currency.country)
            Assert.assertNotNull(
                    "The currency ${currency.name} used by $countryName has a symbol", currency.symbol)
            Assert.assertNotNull(
                    "The currency ${currency.name} used by $countryName has code",
                    currency.code)
            Assert.assertNotNull(
                    "The currency ${currency.name} used by $countryName has name",
                    currency.name)
        }
        log("Currency list size= " + currencies.size)
    }

    @Test
    fun allCountriesHaveFlags() {
        val countries = World.getAllCountries()
        for (country in countries) {
            log("allCountriesHaveFlags: country= ${country.alpha2} flag ${country.flagResource}")
            Assert.assertTrue("There is a flag resource assigned to ${country.name}",
                    country.flagResource > 0)
        }
    }

    @Test
    fun itIsPossibleToGetCountriesFromAContinent() {
        Continent.values().forEach { continent ->
            World.getCountriesOfContinent(continent).forEach { country ->
                Assert.assertEquals(
                        String.format("The country %s is in %s", country.name, continent.title),
                        country.continent, country.continent)
                log(String.format("The country %s is in %s", country.name, continent.title))
            }
        }
    }
}