package nl.darkbyte.country_data

import android.content.Context
import androidx.annotation.RawRes

object AssetsReader {
    /**
     * Read contents from a file in the raw directory
     *
     * @param context    the application context
     * @param resourceID the file name. The file should should be saved inside the raw folder
     * @return a string the content as a string
     * <p>
     * NB: Call this method in a separate thread if calling from the main thread
     **/
    fun readFromAssets(context: Context, @RawRes resID: Int): String? {
        return try {
            context.resources.openRawResource(resID).bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
