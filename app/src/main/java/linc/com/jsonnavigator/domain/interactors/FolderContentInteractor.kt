package linc.com.jsonnavigator.domain.interactors

import io.reactivex.Single
import linc.com.jsonnavigator.domain.models.FilesystemItemModel

interface FolderContentInteractor {
    fun execute(): Single<FilesystemItemModel>
}