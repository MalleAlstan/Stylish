package com.john.stylish.ui.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import com.john.stylish.model.objects.Product.Product
import android.widget.TextView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.john.stylish.R
import com.john.stylish.utils.ImageManager


class HotsListAdapter(private val mHotsList: ArrayList<Any>, context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private val mInflator: LayoutInflater

    init {
        this.mInflator = LayoutInflater.from(context)
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
        // Set title
        holder.textTitle.text = title
    }

    private fun bindFullViewHolder(holder: FullViewHolder, product: Product) {
        // Set main image
//        ImageManager.setImageByUrl(holder.imageMain, product.main_image)
        // Set title
        holder.textTitle.text = product.title
        // Set description
        holder.textDescription.text = product.description
    }

    private fun bindCollageViewHolder(holder: CollageViewHolder, product: Product) {
        // Set left image
        ImageManager.setImageByUrl(holder.imageLeft, if (product.images.size > 0) product.images[0] else product.main_image)
        // Set top image
        ImageManager.setImageByUrl(holder.imageTop, if (product.images.size > 1) product.images[1] else product.main_image)
        // Set bottom image
        ImageManager.setImageByUrl(holder.imageBottom, if (product.images.size > 2) product.images[2] else product.main_image)
        // Set right image
        ImageManager.setImageByUrl(holder.imageRight, if (product.images.size > 3) product.images[3] else product.main_image)
        // Set title
        holder.textTitle.text = product.title
        // Set description
        holder.textDescription.text = product.description
    }

    override fun getItemCount(): Int {
        return mHotsList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            mHotsList[position] is String -> TYPE_TITLE
            position == 1 && mHotsList[position] is Product -> TYPE_FULL
            mHotsList[position] is Product -> TYPE_COLLAGE
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