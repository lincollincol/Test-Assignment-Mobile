package linc.com.jsonnavigator.data

import io.reactivex.Single
import linc.com.jsonnavigator.device.AssetReader
import linc.com.jsonnavigator.domain.models.*
import linc.com.jsonnavigator.domain.repository.JsonFilesystemRepository
import linc.com.jsonnavigator.utils.Constants.Companion.CONTENT
import linc.com.jsonnavigator.utils.Constants.Companion.FOLDER
import linc.com.jsonnavigator.utils.Constants.Companion.ITEMS
import linc.com.jsonnavigator.utils.Constants.Companion.NAME
import linc.com.jsonnavigator.utils.Constants.Companion.TYPE
import org.json.JSONObject

class JsonFilesystemRepositoryImpl(
    private val assetReader: AssetReader
) : JsonFilesystemRepository {

    override fun getFileSystemData(): Single<FilesystemItemModel> {
        return Single.create {
            val jsonFileSystem = assetReader.readJson()
            it.onSuccess(parseJsonFileSystem(jsonFileSystem))
        }
    }

    private fun parseJsonFileSystem(json: String): FilesystemItemModel {
        val item = JSONObject(json)
        val root = FilesystemItemModel(
            item.getString(NAME).toString(),
            item.getString(TYPE).toString()
        )

        if(root.type == FOLDER) {
            val folderItems = item.getJSONArray(ITEMS)
            for (i in 0 until folderItems.length()) {
                root.items.add(parseJsonFileSystem(folderItems[i].toString()))
            }
        }else {
            root.content = item.getString(CONTENT).toString()
        }

        return root
    }


}