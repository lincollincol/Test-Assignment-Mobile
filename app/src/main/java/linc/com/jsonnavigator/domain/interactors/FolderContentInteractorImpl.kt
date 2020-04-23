package linc.com.jsonnavigator.domain.interactors

import linc.com.jsonnavigator.data.JsonFilesystemRepositoryImpl
import linc.com.jsonnavigator.domain.repository.JsonFilesystemRepository

class FolderContentInteractorImpl(
    private val jsonFilesystemRepository: JsonFilesystemRepository
) : FolderContentInteractor {

    override fun execute() = jsonFilesystemRepository.getFileSystemData()

}