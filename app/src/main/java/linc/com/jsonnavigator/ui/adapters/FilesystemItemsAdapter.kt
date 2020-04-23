package linc.com.jsonnavigator.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import linc.com.jsonnavigator.R
import linc.com.jsonnavigator.domain.models.FilesystemItemModel
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

        val name = itemView.findViewById<TextView>(R.id.itemName)

        fun bind(filesystemItem: FilesystemItemModel) {
            this.name.text = filesystemItem.name
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            clickListener.onClick(filesystemItems[adapterPosition])
        }

    }

    interface OnFilesystemItemClickListener {
        fun onClick(filesystemItem: FilesystemItemModel)
    }
}