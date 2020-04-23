package linc.com.jsonnavigator.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RawRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import linc.com.jsonnavigator.R
import linc.com.jsonnavigator.domain.models.FilesystemItemModel
import linc.com.jsonnavigator.utils.Constants.Companion.DOCX
import linc.com.jsonnavigator.utils.Constants.Companion.FOLDER
import linc.com.jsonnavigator.utils.Constants.Companion.PDF
import linc.com.jsonnavigator.utils.Constants.Companion.XLSX
import linc.com.jsonnavigator.utils.PathFormatter
import linc.com.jsonnavigator.utils.updateAll

class FilesystemItemsAdapter : RecyclerView.Adapter<FilesystemItemsAdapter.FilesystemItemViewHolder>() {

    lateinit var clickListener: OnFilesystemItemClickListener
    private val filesystemItems = mutableListOf<FilesystemItemModel>()

    fun setData(filesystemItems: MutableList<FilesystemItemModel>) {
        this.filesystemItems.updateAll(filesystemItems)
        notifyDataSetChanged()
    }

    fun setOnFilesytemItemClickListener(clickListener: OnFilesystemItemClickListener) {
        this.clickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FilesystemItemViewHolder (
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_filesystem, parent, false)
    )

    override fun getItemCount() = filesystemItems.count()

    override fun onBindViewHolder(holder: FilesystemItemViewHolder, position: Int) {
        holder.bind(filesystemItems[position])
    }

    inner class FilesystemItemViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val name = itemView.findViewById<TextView>(R.id.itemName)
        private val icon = itemView.findViewById<ImageView>(R.id.itemIcon)

        fun bind(filesystemItem: FilesystemItemModel) {
            val context = itemView.context
            this.name.text = filesystemItem.name

            if(filesystemItem.type == FOLDER) {
                icon.setBackgroundResource(R.drawable.ic_folder)
            }else {
                val fileIcon =
                    when(PathFormatter.getFileExt(filesystemItem.name)) {
                        PDF -> R.drawable.ic_pdf
                        DOCX -> R.drawable.ic_doc
                        XLSX -> R.drawable.ic_xlsx
                        else -> R.drawable.ic_file
                    }
                icon.setBackgroundResource(fileIcon)
            }

            this.itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            clickListener.onClick(filesystemItems[adapterPosition])
        }

    }

    interface OnFilesystemItemClickListener {
        fun onClick(filesystemItem: FilesystemItemModel)
    }
}