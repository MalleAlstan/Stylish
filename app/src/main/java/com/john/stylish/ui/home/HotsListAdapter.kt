package com.john.stylish.ui.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import com.john.stylish.model.objects.Product.Product
import android.widget.TextView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.john.stylish.R
import com.john.stylish.utils.ImageManager


class HotsListAdapter(private val mHotsList: ArrayList<Any>, private val mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private val mInflator: LayoutInflater

    init {
        this.mInflator = LayoutInflater.from(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_TITLE -> TitleViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_hots_title, parent, false))
            TYPE_FULL -> FullViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_hots_full, parent, false))
            TYPE_COLLAGE -> CollageViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_hots_collage, parent, false))
            else -> LoadingViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_all_loading, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TitleViewHolder -> bindTitleViewHolder(holder, mHotsList[position] as String)
            is FullViewHolder -> bindFullViewHolder(holder, mHotsList[position] as Product)
            is CollageViewHolder -> bindCollageViewHolder(holder, mHotsList[position] as Product)
        }
    }

    private fun bindTitleViewHolder(holder: TitleViewHolder, title: String) {
        holder.textTitle.text = title
    }

    private fun bindFullViewHolder(holder: FullViewHolder, product: Product) {
        Glide.with(mContext).load(product.main_image).into(holder.imageMain)
        holder.textTitle.text = product.title
        holder.textDescription.text = product.description
    }

    private fun bindCollageViewHolder(holder: CollageViewHolder, product: Product) {

        Glide.with(mContext).load( if (product.images.isNotEmpty()) product.images[0] else product.main_image).into(holder.imageLeft)
        Glide.with(mContext).load( if (product.images.size > 1) product.images[1] else product.main_image).into(holder.imageTop)
        Glide.with(mContext).load( if (product.images.size > 2) product.images[2] else product.main_image).into(holder.imageBottom)
        Glide.with(mContext).load( if (product.images.size > 3) product.images[3] else product.main_image).into(holder.imageRight)

        holder.textTitle.text = product.title
        holder.textDescription.text = product.description
    }

    override fun getItemCount(): Int {
        return mHotsList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            mHotsList[position] is String -> TYPE_TITLE
            position%2 == 1 && mHotsList[position] is Product -> TYPE_FULL
            position%2 == 0 && mHotsList[position] is Product -> TYPE_COLLAGE
//            position == 1 && mHotsList[position] is Product -> TYPE_FULL
//            mHotsList[position] is Product -> TYPE_COLLAGE
            else -> TYPE_LOADING
        }
    }

    private inner class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textTitle: TextView = itemView.findViewById(R.id.text_hots_title)
    }

    private inner class FullViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageMain: ImageView = itemView.findViewById(R.id.image_hots_main)
        val textTitle: TextView = itemView.findViewById(R.id.text_hots_title)
        val textDescription: TextView = itemView.findViewById(R.id.text_hots_description)

        init {
//            itemView.findViewById<View>(R.id.layout_hots_full).setOnClickListener {
//                presenter.openDetail(hotsDataList[adapterPosition] as Product) }
        }
    }

    private inner class CollageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageLeft: ImageView = itemView.findViewById(R.id.image_hots_left)
        val imageTop: ImageView = itemView.findViewById(R.id.image_hots_top)
        val imageBottom: ImageView = itemView.findViewById(R.id.image_hots_bottom)
        val imageRight: ImageView = itemView.findViewById(R.id.image_hots_right)
        val textTitle: TextView = itemView.findViewById(R.id.text_hots_title)
        val textDescription: TextView = itemView.findViewById(R.id.text_hots_description)

        init {
//            itemView.findViewById<View>(R.id.layout_hots_collage).setOnClickListener {
//                presenter.openDetail(hotsDataList[adapterPosition] as Product) }
        }
    }

    private inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {

        private const val TYPE_LOADING = 0
        private const val TYPE_TITLE   = 0x01
        private const val TYPE_FULL    = 0x02
        private const val TYPE_COLLAGE = 0x03
    }

}