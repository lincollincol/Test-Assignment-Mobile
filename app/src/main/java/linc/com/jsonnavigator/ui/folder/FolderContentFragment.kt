package linc.com.jsonnavigator.ui.folder


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import linc.com.jsonnavigator.R
import linc.com.jsonnavigator.data.JsonFilesystemRepository
import linc.com.jsonnavigator.device.AssetReader
import linc.com.jsonnavigator.domain.interactors.FolderContentInteractorImpl
import linc.com.jsonnavigator.domain.models.FilesystemItemModel
import linc.com.jsonnavigator.ui.NavigatorActivity
import linc.com.jsonnavigator.ui.adapters.FilesystemItemsAdapter
import linc.com.jsonnavigator.ui.file.FileContentFragment
import linc.com.jsonnavigator.utils.isNull
import linc.com.jsonnavigator.utils.toList

class FolderContentFragment : Fragment(), FolderContentView, FilesystemItemsAdapter.OnFilesystemItemClickListener {

    private lateinit var filesystemAdapter: FilesystemItemsAdapter
    private var presenter: FolderContentPresenter? = null

    companion object {
        fun newInstance(bundle: Bundle) = FolderContentFragment().apply {
            arguments = bundle
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(presenter == null ) {
            presenter = FolderContentPresenter(
                FolderContentInteractorImpl(
                    JsonFilesystemRepository(
                        AssetReader(activity!!.assets)
                    )
                )
            )
        }
        presenter?.bind(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_folder_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filesystemAdapter = FilesystemItemsAdapter()
            .apply {
                setOnFilesytemItemClickListener(this@FolderContentFragment)
            }

        val recyclerView = view.findViewById<RecyclerView>(R.id.filesystemItems)
            .apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = filesystemAdapter
                setHasFixedSize(true)
            }

        presenter?.getData(arguments?.isEmpty ?: true)
    }

    override fun showRootFolders(folders: MutableList<FilesystemItemModel>) {
        filesystemAdapter.setData(folders)
    }

    override fun showChildFolders() {
        val data = arguments!!.getParcelable<FilesystemItemModel>("FOLDER")
        filesystemAdapter.setData(
            data!!.items
        )
    }

    override fun openFolder(folder: FilesystemItemModel) {
        (activity as NavigatorActivity).navigateToFragment(
            fragment = FolderContentFragment.newInstance(Bundle().apply {
                putParcelable("FOLDER", folder)
            }),
            withBackStack = true,
            name = folder.name
        )
    }

    override fun openFile(file: FilesystemItemModel) {
        println("OPEN_FI:E")
        (activity as NavigatorActivity).navigateToFragment(
            fragment = FileContentFragment.newInstance(Bundle().apply {
                putParcelable("FILE", file)
            }),
            withBackStack = true,
            name = file.name
        )
    }

    override fun onClick(filesystemItem: FilesystemItemModel) {
        println(filesystemItem.name)
        presenter?.openFilesystemItem(filesystemItem)
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter?.unbind()
    }

}
