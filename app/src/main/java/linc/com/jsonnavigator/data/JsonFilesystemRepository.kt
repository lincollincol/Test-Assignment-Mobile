package linc.com.jsonnavigator.data

import io.reactivex.Single
import linc.com.jsonnavigator.device.AssetReader
import linc.com.jsonnavigator.domain.models.*
import org.json.JSONObject

class JsonFilesystemRepository(
    private val assetReader: AssetReader
) {

    fun getFileSystemData(): Single<FilesystemItemModel> {
        return Single.create {
            println("START JSIN")
            val jsonFileSystem = assetReader.readJson()
            it.onSuccess(
                parseJsonFileSystem(jsonFileSystem)
            )
        }
    }

    private fun parseJsonFileSystem(json: String): FilesystemItemModel {
        //todo hardcode to const

        val obj = JSONObject(json)

        val root = FilesystemItemModel(
            obj.getString("name").toString(),
            obj.getString("type").toString()
        )

        if(root.type == "FOLDER") {
            val folderItems = obj.getJSONArray("items")
            for (i in 0 until folderItems.length()) {
                root.items.add(parseJsonFileSystem(folderItems[i].toString()))
            }
        }else {
            root.content = obj.getString("content").toString()
        }

        return root
    }


}