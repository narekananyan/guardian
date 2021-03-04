package am.codeschool.theguardianapi.ui.adapters

import am.codeschool.theguardianapi.R
import am.codeschool.theguardianapi.model.data.pojo.Content
import am.codeschool.theguardianapi.model.room.entity.ContentRoom
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlin.collections.ArrayList

class ContentAdapter : RecyclerView.Adapter<ContentAdapter.BaseViewHolder>() {
    private var mutableList = mutableListOf<Content.Response.Result>()
    private lateinit var layoutInflater: LayoutInflater
    private var listener = arrayListOf<OnContentItemClickListener>()
    private lateinit var listenerFav: OnContentFavoriteClickListener

    interface OnContentFavoriteClickListener {
        fun onClick(item: Content.Response.Result, position: Int, isChecked: Boolean)
    }

    interface OnContentItemClickListener {
        fun callForDescription(url: String)

    }

    fun setOnItemClickListener(listener: OnContentItemClickListener) {
        this.listener.add(listener)
    }

    fun setOnSectionFavClickListener(listener: OnContentFavoriteClickListener) {
        this.listenerFav = listener
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        layoutInflater = LayoutInflater.from(recyclerView.context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view =
            layoutInflater.inflate(R.layout.item_content_holder, parent, false)
        return ContentViewHolder(view, listener, listenerFav)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(mutableList[position])
    }

    override fun getItemCount() = mutableList.size
    abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: Any)
    }

    class ContentViewHolder(
        itemView: View,
        private val listener: ArrayList<OnContentItemClickListener>,
        private var listenerFav: OnContentFavoriteClickListener
    ) : BaseViewHolder(itemView) {

        lateinit var imageItem: ImageView
        lateinit var titleItem: TextView
        lateinit var secondaryTextItem: TextView
        lateinit var url: String
        lateinit var favorite: CheckBox
        override fun bind(item: Any) {
            if (item !is Content.Response.Result) return
            url = item.id
            Log.d("item", url)
            imageItem = itemView.findViewById(R.id.imageItem)
            titleItem = itemView.findViewById(R.id.titleItem)
            secondaryTextItem = itemView.findViewById(R.id.secondaryTextItem)
            favorite = itemView.findViewById(R.id.contentFavorite)
            titleItem.text = item.sectionName
            secondaryTextItem.text = item.webTitle
            favorite.isChecked = item.favorite

            if (item.fields != null) {
                Glide.with(itemView.context)
                    .asBitmap()
                    .load(item.fields.thumbnail)
                    .into(imageItem)
            } else {
                imageItem.visibility = View.GONE
            }
            setListeners(itemView, item)
        }

        private fun setListeners(itemView: View, item: Content.Response.Result) {
            itemView.setOnClickListener {
                listener.forEach {
                    it.callForDescription(item.id)
                }
            }
            favorite.setOnClickListener {
                it as CheckBox
                if (it.isChecked) {
                    item.favorite = it.isChecked
                    it.isChecked = true
                } else {
                    item.favorite = it.isChecked
                    it.isChecked = false
                }

                listenerFav.onClick(item, adapterPosition, it.isChecked)
            }
        }
    }

    fun update(list: List<Content.Response.Result>?) {
        list?.let {
            mutableList.clear()
            mutableList.addAll(list)
            notifyDataSetChanged()
        }
    }

    fun favChanged(list: List<ContentRoom>) {
        mutableList.forEach { result ->
            result.favorite = false
            list.forEach {
                if (result.id == it.id)
                    result.favorite = true

            }
        }
        notifyDataSetChanged()

    }
}