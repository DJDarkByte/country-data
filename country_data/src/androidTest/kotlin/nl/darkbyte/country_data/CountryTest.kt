package nl.darkbyte.country_data

import androidx.test.platform.app.InstrumentationRegistry
import nl.darkbyte.country_data.model.Continent
import nl.darkbyte.country_data.model.Country
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CountryTest {
    private lateinit var sweden: Country

    @Before
    fun setUp() {
        World.init(InstrumentationRegistry.getInstrumentation().context)
        sweden = World.getCountryFrom("se")
    }

    @Test
    fun getContinent() {
        assertEquals(Continent.EUROPE, sweden.continent)
    }

    @Test
    fun getName() {
        Assert.assertEquals("Sweden", sweden.name)
    }

    @Test
    fun getAlpha2() {
        assertEquals("se", sweden.alpha2)
    }

    @Test
    fun getAlpha3() {
        assertEquals("swe", sweden.alpha3)
    }

    @Test
    fun getLocalizedName() {
        assertEquals("Sweden", sweden.localizedName)
    }
}