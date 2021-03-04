package am.codeschool.theguardianapi.ui.adapters.subadapters

import am.codeschool.theguardianapi.R
import am.codeschool.theguardianapi.model.room.entity.ContentRoom
import am.codeschool.theguardianapi.model.room.entity.SectionRoom
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FavContentAdapter: RecyclerView.Adapter<FavContentAdapter.FavContentVH>() {
    private lateinit var inflayter: LayoutInflater
    private var favList = mutableListOf<ContentRoom>()
    private lateinit var buttonClickListener:OnDeleteButtonClick
    private lateinit var onitemCliskListener:OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(id:String)
    }

    interface OnDeleteButtonClick{
        fun onClick(itemId: String)
    }
    fun setDeleteClickListener(buttonClickListener:OnDeleteButtonClick){
        this.buttonClickListener= buttonClickListener
    }
    fun setOnItemClickListener(onitemCliskListener:OnItemClickListener){
        this.onitemCliskListener = onitemCliskListener
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        inflayter = LayoutInflater.from(recyclerView.context)
        super.onAttachedToRecyclerView(recyclerView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavContentVH {
        val view = inflayter.inflate(R.layout.item_favcontent,parent,false)
        return FavContentVH(view,buttonClickListener,onitemCliskListener)
    }

    override fun onBindViewHolder(holder: FavContentVH, position: Int) {
        holder.bind(favList[position])
    }

    override fun getItemCount()= favList.size

    class FavContentVH(itemView: View,private val buttonClickListener:OnDeleteButtonClick, private val onitemCliskListener:OnItemClickListener):RecyclerView.ViewHolder(itemView) {
        private val imageItem: ImageView = itemView.findViewById(R.id.favImageItem)
        private val titleItem: TextView = itemView.findViewById(R.id.favTitleItem)
        private val secondaryTextItem: TextView = itemView.findViewById(R.id.favSecondaryTextItem)
//        private val url: String
        private val deleteItem: ImageButton = itemView.findViewById(R.id.favContentDelete)
            fun bind(item: ContentRoom){
                if (item.thumbnail.trim().isNotEmpty()) {
                    Glide.with(itemView.context)
                        .asBitmap()
                        .load(item.thumbnail)
                        .into(imageItem)
                } else {
                    imageItem.visibility = View.GONE
                }
                titleItem.text = item.sectionName
                secondaryTextItem.text = item.webTitle
                setupListneres(item,itemView)
            }

        private fun setupListneres(item:ContentRoom,itemView: View) {
            deleteItem.setOnClickListener {
                buttonClickListener.onClick(item.id)
            }
            itemView.setOnClickListener {
                onitemCliskListener.onItemClick(item.id)
            }
        }

    }
    fun updateList(list: List<ContentRoom>){
        favList.clear()
        favList.addAll(list)
        notifyDataSetChanged()
    }
}