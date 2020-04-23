package linc.com.jsonnavigator.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class FilesystemItemModel(
    val name: String,
    val type: String,
    val items: MutableList<FilesystemItemModel> = mutableListOf(),
    var content: String? = null
) : Parcelable