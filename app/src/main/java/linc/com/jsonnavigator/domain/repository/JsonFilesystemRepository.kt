package linc.com.jsonnavigator.domain.repository

import io.reactivex.Single
import linc.com.jsonnavigator.domain.models.FilesystemItemModel

interface JsonFilesystemRepository {
    fun getFileSystemData(): Single<FilesystemItemModel>
}