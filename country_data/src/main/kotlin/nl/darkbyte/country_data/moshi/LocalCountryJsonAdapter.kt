// Code generated by moshi-kotlin-codegen. Do not edit.
@file:Suppress(
    "DEPRECATION", "unused", "ClassName", "REDUNDANT_PROJECTION",
    "RedundantExplicitType", "LocalVariableName", "RedundantVisibilityModifier",
    "PLATFORM_CLASS_MAPPED_TO_KOTLIN", "IMPLICIT_NOTHING_TYPE_ARGUMENT_IN_RETURN_POSITION"
)

package nl.darkbyte.country_data.moshi

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.internal.Util
import nl.darkbyte.country_data.R
import nl.darkbyte.country_data.model.Continent
import nl.darkbyte.country_data.model.Country
import kotlin.Int
import kotlin.String

internal class LocalCountryJsonAdapter(private val context: Context, moshi: Moshi) : JsonAdapter<Country>() {
    private val options: JsonReader.Options =
        JsonReader.Options.of("name", "alpha2", "alpha3", "continent")

    private val stringAdapter: JsonAdapter<String> = moshi.adapter(String::class.java)

    private val continentAdapter: JsonAdapter<Continent> = moshi.adapter(
        Continent::class.java,
        emptySet(), "continent"
    )

    private val intAdapter: JsonAdapter<Int> = moshi.adapter(
        Int::class.java, emptySet(),
        "flagResource"
    )

    public override fun fromJson(reader: JsonReader): Country {
        var name: String? = null
        var alpha2: String? = null
        var alpha3: String? = null
        var continent: Continent? = null
        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.selectName(options)) {
                0 -> name = reader.nextString()
                    ?: throw Util.unexpectedNull(
                        "name", "name",
                        reader
                    )
                1 -> alpha2 = stringAdapter.fromJson(reader) ?: throw Util.unexpectedNull(
                    "alpha2",
                    "alpha2", reader
                )
                2 -> alpha3 = stringAdapter.fromJson(reader) ?: throw Util.unexpectedNull(
                    "alpha3",
                    "alpha3", reader
                )
                3 -> continent = continentAdapter.fromJson(reader)
                    ?: throw Util.unexpectedNull(
                        "continent",
                        "continent", reader
                    )
                -1 -> {
                    // Unknown name, skip it.
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }
        reader.endObject()

        val safeAlpha2 = alpha2?.lowercase()
            ?: throw Util.missingProperty("alpha2", "alpha2", reader)
        val flagResource =
            context.resources.getIdentifier("drawable/flag_$safeAlpha2", null, context.packageName)
                .takeIf { it > 0 }
                ?: R.drawable.flag_globe

        return Country(
            name = name ?: throw Util.missingProperty("name", "name", reader),
            alpha2 = safeAlpha2,
            alpha3 = alpha3?.lowercase() ?: throw Util.missingProperty("alpha3", "alpha3", reader),
            continent = continent
                ?: throw Util.missingProperty("continent", "continent", reader),
            flagResource = flagResource,
        )
    }

    public override fun toJson(writer: JsonWriter, value_: Country?) {
        if (value_ == null) {
            throw NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("name")
        stringAdapter.toJson(writer, value_.name)
        writer.name("alpha2")
        stringAdapter.toJson(writer, value_.alpha2)
        writer.name("alpha3")
        stringAdapter.toJson(writer, value_.alpha3)
        writer.name("continent")
        continentAdapter.toJson(writer, value_.continent)
        writer.name("flagResource")
        intAdapter.toJson(writer, value_.flagResource)
        writer.endObject()
    }
}