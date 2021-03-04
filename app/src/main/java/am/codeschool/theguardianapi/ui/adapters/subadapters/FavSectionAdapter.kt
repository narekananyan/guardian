package am.codeschool.theguardianapi.ui.adapters.subadapters

import am.codeschool.theguardianapi.R
import am.codeschool.theguardianapi.model.data.pojo.Content
import am.codeschool.theguardianapi.model.room.entity.SectionRoom
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FavSectionAdapter: RecyclerView.Adapter<FavSectionAdapter.FavSectionVH>() {
    private lateinit var inflayter:LayoutInflater
    private var favList = mutableListOf<SectionRoom>()
    private lateinit var buttonClickListener:OnDeleteButtonClick

    interface OnDeleteButtonClick{
        fun onClick(item:SectionRoom)
    }
    fun setDeleteClickListener(buttonClickListener:OnDeleteButtonClick){
        this.buttonClickListener= buttonClickListener
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        inflayter = LayoutInflater.from(recyclerView.context)
        super.onAttachedToRecyclerView(recyclerView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavSectionVH {
        val view = inflayter.inflate(R.layout.item_favsec,parent,false)
        return FavSectionVH(view,buttonClickListener)
    }

    override fun onBindViewHolder(holder: FavSectionVH, position: Int) {
        holder.bind(favList[position])
    }

    override fun getItemCount()=favList.size

    class FavSectionVH(itemView: View,private val buttonClickListener:OnDeleteButtonClick): RecyclerView.ViewHolder(itemView) {
        private var titleSec = itemView.findViewById<TextView>(R.id.favSectionTitle)
        private var deleteButton: ImageButton = itemView.findViewById(R.id.deleteFavSec)
        fun bind(item:SectionRoom){
            titleSec.text = item.webTitle
            setUpListener(deleteButton,itemView,item)
        }
        private fun setUpListener(deleteButton: ImageButton,itemView: View,item:SectionRoom){
            deleteButton.setOnClickListener {
                buttonClickListener.onClick(item)
            }

        }
    }
    fun updateList(list: List<SectionRoom>){
        favList.clear()
        favList.addAll(list)
        notifyDataSetChanged()
    }
}