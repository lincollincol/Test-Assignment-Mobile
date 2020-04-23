package linc.com.jsonnavigator.ui.folder

import linc.com.jsonnavigator.domain.models.FilesystemItemModel

interface FolderContentView {

    fun showRootFolders(folders: MutableList<FilesystemItemModel>)
    fun showChildFolders()
    fun openFolder(folder: FilesystemItemModel)
    fun openFile(file: FilesystemItemModel)

}