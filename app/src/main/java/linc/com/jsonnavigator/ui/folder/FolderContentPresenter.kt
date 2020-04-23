package linc.com.jsonnavigator.ui.folder

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import linc.com.jsonnavigator.domain.interactors.FolderContentInteractor
import linc.com.jsonnavigator.domain.models.FilesystemItemModel
import linc.com.jsonnavigator.utils.Constants.Companion.FOLDER
import linc.com.jsonnavigator.utils.toList

class FolderContentPresenter(
    private val folderContentInteractor: FolderContentInteractor
) {

    private val disposableBag = CompositeDisposable()
    private var view: FolderContentView? = null

    fun bind(view: FolderContentView) {
        this.view = view
    }

    fun unbind() {
        this.view = null
        if (!disposableBag.isDisposed) {
            disposableBag.dispose()
        }
    }

    fun getData(isRoot: Boolean) {
        if(!isRoot) {
            view?.showChildFolders()
            return
        }
        disposableBag.add(
            folderContentInteractor.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.showRootFolders(it.toList())
                }, {
                    view?.showError(it.localizedMessage)
                })
        )
    }

    fun openFilesystemItem(filesystemItem: FilesystemItemModel) {
        println(filesystemItem.type)
        println(filesystemItem.name)
        if(filesystemItem.type == FOLDER) {
            view?.openFolder(filesystemItem)
        }else {
            view?.openFile(filesystemItem)
        }
    }

}