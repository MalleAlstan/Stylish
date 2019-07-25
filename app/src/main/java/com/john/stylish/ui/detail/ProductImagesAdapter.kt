package com.john.stylish.ui.detail

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.john.stylish.R


class ProductImagesAdapter(val mContext: Context, val mImages: Array<String>)
    : RecyclerView.Adapter<ProductImagesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_detail_images, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(mContext).load(mImages.get(position % mImages.size)).into(holder.image)
    }

    override fun getItemCount(): Int {
        return Integer.MAX_VALUE/2
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var image = itemView.findViewById<ImageView>(R.id.image_product_photo)
    }
}