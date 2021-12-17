package nl.darkbyte.country_data

import android.content.Context
import androidx.startup.Initializer

class WorldInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        World.init(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}