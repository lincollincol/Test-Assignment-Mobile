package linc.com.jsonnavigator.domain.interactors

import linc.com.jsonnavigator.data.JsonFilesystemRepository

class FolderContentInteractorImpl(
    private val jsonFilesystemRepository: JsonFilesystemRepository
) {

    fun readJson() = jsonFilesystemRepository.getFileSystemData()

}