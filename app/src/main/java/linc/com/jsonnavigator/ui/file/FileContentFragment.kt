package linc.com.jsonnavigator.ui.file

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import linc.com.jsonnavigator.R
import linc.com.jsonnavigator.domain.models.FilesystemItemModel
import linc.com.jsonnavigator.ui.folder.FolderContentFragment

class FileContentFragment : Fragment() {

    companion object {
        fun newInstance(bundle: Bundle) = FileContentFragment().apply {
            arguments = bundle
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_file_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arguments!!.getParcelable<FilesystemItemModel>("FILE")
        val content = view.findViewById<TextView>(R.id.content).apply {
            text = data!!.content
        }

    }

}
