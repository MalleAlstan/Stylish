package com.john.stylish.ui.detail

import com.john.stylish.model.objects.Product.Color
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.john.stylish.R


class ProductColorsAdapter(val mColors: Array<Color>)
    : RecyclerView.Adapter<ProductColorsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_detail_colors, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image.setBackgroundColor(android.graphics.Color.parseColor("#" + mColors.get(position).code))
    }

    override fun getItemCount(): Int {
        return mColors.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var image = itemView.findViewById<ImageView>(R.id.image_product_color)
    }
}