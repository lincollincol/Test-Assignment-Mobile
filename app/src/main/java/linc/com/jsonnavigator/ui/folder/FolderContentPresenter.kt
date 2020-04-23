package linc.com.jsonnavigator.ui.folder

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import linc.com.jsonnavigator.domain.interactors.FolderContentInteractorImpl
import linc.com.jsonnavigator.domain.models.FilesystemItemModel
import linc.com.jsonnavigator.utils.toList

class FolderContentPresenter(
    private val folderContentInteractorImpl: FolderContentInteractorImpl
) {

    private var view: FolderContentView? = null

    fun bind(view: FolderContentView) {
        this.view = view
    }

    fun unbind() {
        this.view = null
    }

    fun getData(isRoot: Boolean) {
        println("IS ROOT $isRoot")
        if(!isRoot) {
            view?.showChildFolders()
            return
        }

        folderContentInteractorImpl.readJson()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.showRootFolders(it.toList())
                println("SUCCESS")
            }, {
                println("ERROR")
            })
    }

    fun openFilesystemItem(filesystemItem: FilesystemItemModel) {
        println(filesystemItem.type)
        println(filesystemItem.name)
        if(filesystemItem.type == "FOLDER") {
            view?.openFolder(filesystemItem)
        }else {
            view?.openFile(filesystemItem)
        }
    }

}