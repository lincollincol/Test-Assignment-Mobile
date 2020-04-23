package linc.com.jsonnavigator.device

import android.content.res.AssetManager
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets


class AssetReader(
    private val assetManager: AssetManager
) {

    fun readJson(): String {
        // todo to constants
        val inputStream = assetManager.open("filesystem-sample.json")

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