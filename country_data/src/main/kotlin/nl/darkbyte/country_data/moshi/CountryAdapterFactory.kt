package nl.darkbyte.country_data.moshi

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import nl.darkbyte.country_data.model.Country
import java.lang.reflect.Type

internal class CountryAdapterFactory(private val context: Context) : JsonAdapter.Factory {
    override fun create(
        type: Type,
        annotations: MutableSet<out Annotation>,
        moshi: Moshi
    ): JsonAdapter<*>? {
        return when (type) {
            Country::class.java -> CountryAdapter(context, moshi)
            else -> null
        }
    }
}