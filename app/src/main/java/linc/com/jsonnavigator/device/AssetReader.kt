package linc.com.jsonnavigator.device

import android.content.res.AssetManager
import linc.com.jsonnavigator.utils.Constants.Companion.SOURCE_FILE
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

class AssetReader(
    private val assetManager: AssetManager
) {

    fun readJson(): String {
        val inputStream = assetManager.open(SOURCE_FILE)
        val jsonData = StringBuilder()

        BufferedReader(
            InputStreamReader(
                inputStream,
                Charset.forName(StandardCharsets.UTF_8.name())
            )
        ).use { reader ->
            var c = 0
            while (reader.read().also { c = it } != -1) {
                jsonData.append(c.toChar())
            }
        }

        return jsonData.toString()
    }

}