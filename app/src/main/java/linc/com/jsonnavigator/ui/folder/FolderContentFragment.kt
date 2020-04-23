package linc.com.jsonnavigator.ui.folder

import android.os.Bundle
import android.transition.Fade
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import linc.com.jsonnavigator.R
import linc.com.jsonnavigator.data.JsonFilesystemRepositoryImpl
import linc.com.jsonnavigator.device.AssetReader
import linc.com.jsonnavigator.domain.interactors.FolderContentInteractorImpl
import linc.com.jsonnavigator.domain.models.FilesystemItemModel
import linc.com.jsonnavigator.ui.NavigatorActivity
import linc.com.jsonnavigator.ui.adapters.FilesystemItemsAdapter
import linc.com.jsonnavigator.ui.file.FileContentFragment
import linc.com.jsonnavigator.utils.Constants.Companion.DURATION_SMALL
import linc.com.jsonnavigator.utils.Constants.Companion.KEY_FILE
import linc.com.jsonnavigator.utils.Constants.Companion.KEY_FOLDER

class FolderContentFragment : Fragment(), FolderContentView, FilesystemItemsAdapter.OnFilesystemItemClickListener {

    private lateinit var filesystemAdapter: FilesystemItemsAdapter
    private lateinit var noItems: TextView
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
                    JsonFilesystemRepositoryImpl(
                        AssetReader(activity!!.assets)
                    )
                )
            )
        }

        presenter?.bind(this)

        enterTransition = Fade(Fade.IN).apply {
            interpolator = LinearInterpolator()
            duration = DURATION_SMALL
        }

        reenterTransition = Fade(Fade.IN).apply {
            interpolator = LinearInterpolator()
            duration = DURATION_SMALL
        }

        exitTransition = Fade(Fade.OUT).apply {
            interpolator = LinearInterpolator()
            duration = DURATION_SMALL
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_folder_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filesystemAdapter = FilesystemItemsAdapter().apply {
            setOnFilesytemItemClickListener(this@FolderContentFragment)
        }

        view.findViewById<RecyclerView>(R.id.filesystemItems)
            .apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = filesystemAdapter
                setHasFixedSize(true)
            }

        noItems = view.findViewById<TextView>(R.id.noItems)
        presenter?.getData(arguments?.isEmpty ?: true)
    }

    override fun showRootFolders(folders: MutableList<FilesystemItemModel>) {
        filesystemAdapter.setData(folders)
    }

    override fun showChildFolders() {
        val data = arguments!!.getParcelable<FilesystemItemModel>(KEY_FOLDER)
        filesystemAdapter.setData(data!!.items)
        if(data.items.isEmpty()) noItems.visibility = View.VISIBLE
    }

    override fun openFolder(folder: FilesystemItemModel) {
        (activity as NavigatorActivity).navigateToFragment(
            fragment = FolderContentFragment.newInstance(Bundle().apply {
                putParcelable(KEY_FOLDER, folder)
            }),
            withBackStack = true,
            name = folder.name
        )
    }

    override fun openFile(file: FilesystemItemModel) {
        (activity as NavigatorActivity).navigateToFragment(
            fragment = FileContentFragment.newInstance(Bundle().apply {
                putParcelable(KEY_FILE, file)
            }),
            withBackStack = true,
            name = file.name
        )
    }

    override fun showError(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
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
