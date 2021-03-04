package am.codeschool.theguardianapi.ui.adapters

import am.codeschool.theguardianapi.R
import am.codeschool.theguardianapi.model.data.pojo.Section
import am.codeschool.theguardianapi.model.room.entity.SectionRoom
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class SectionAdapter : RecyclerView.Adapter<SectionAdapter.BaseViewHolder>() {
    private var mutableList = mutableListOf<Section.Response.Result>()
    private lateinit var layoutInflater: LayoutInflater
    private var listener = arrayListOf<OnSectionItemClickListener>()
    private lateinit var listenerFav: OnSectionFavoriteClickListener

    interface OnSectionFavoriteClickListener {
        fun onClick(item: Section.Response.Result,position: Int,isChecked:Boolean)
    }

    interface OnSectionItemClickListener {
        fun callSectionClick(url: String)
    }

    fun setOnSectionItemClickListener(listener: OnSectionItemClickListener) {
        this.listener.add(listener)
    }

    fun setOnSectionFavClickListener(listener: OnSectionFavoriteClickListener) {
        this.listenerFav = listener
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        layoutInflater = LayoutInflater.from(recyclerView.context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view =
            layoutInflater.inflate(R.layout.item_section_holder, parent, false)
        return SectionViewHolder(view, listener, listenerFav)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(mutableList[position])
    }

    override fun getItemCount() = mutableList.size
    abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: Any)
    }

    class SectionViewHolder(
        itemView: View,
        private val listener: ArrayList<OnSectionItemClickListener>,
        private val listenerFav: OnSectionFavoriteClickListener
    ) : BaseViewHolder(itemView) {
        private var titleText = itemView.findViewById<TextView>(R.id.sectionTextId)
        val ratingIcon =itemView.findViewById<AppCompatCheckBox>(R.id.sectionFavorite)


        @SuppressLint("SetTextI18n")
        override fun bind(item: Any) {
            if (item is Section.Response.Result) {
                titleText.text = item.webTitle
                if (item.favorite){
                    ratingIcon.isChecked = false
                }else if (!item.favorite){
                    ratingIcon.isChecked = true
                }
                ratingIcon.isChecked = item.favorite
               setupListeners(itemView, item, ratingIcon)
            }
        }

        private fun setupListeners(
            itemView: View,
            item: Section.Response.Result,
            ratingIcon: AppCompatCheckBox
        ) {
            itemView.setOnClickListener {
                listener.forEach {
                    it.callSectionClick(item.id)
                }
            }

            ratingIcon.setOnClickListener {
             it as AppCompatCheckBox
                if(it.isChecked){
                    item.favorite=it.isChecked
                    it.isChecked=true
                }else{
                    item.favorite=it.isChecked
                    it.isChecked=false
                }


                listenerFav.onClick(item,adapterPosition,it.isChecked)
            }
        }
    }
    fun favChanged(list:List<SectionRoom>){
        mutableList.forEach { result ->
            result.favorite=false
            list.forEach {
                if (result.id==it.id)
                    result.favorite=true

            }
        }
        notifyDataSetChanged()
    }
    fun update(list: List<Section.Response.Result>?) {
        list?.let {
            mutableList.clear()
            mutableList.addAll(list)
            notifyDataSetChanged()
        }
    }

}