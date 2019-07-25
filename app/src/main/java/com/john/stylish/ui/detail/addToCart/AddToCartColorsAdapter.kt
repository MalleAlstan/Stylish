package com.john.stylish.ui.detail

import android.arch.lifecycle.Observer
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.ImageView
import com.john.stylish.R
import com.john.stylish.Stylish
import com.john.stylish.model.objects.Product.Color
import com.john.stylish.ui.catalog.CatalogProductsAdapter
import com.john.stylish.ui.detail.addToCart.AddToCartDialogViewModel
import kotlinx.android.synthetic.main.frag_men.*


class AddToCartColorsAdapter(val mColors: Array<Color>, val mViewModel: AddToCartDialogViewModel)
    : RecyclerView.Adapter<AddToCartColorsAdapter.ViewHolder>() {

    var mSelectedColor = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_add_to_cart_colors, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image.setBackgroundColor(android.graphics.Color.parseColor("#" + mColors.get(position).code))
        holder.image.setOnClickListener(View.OnClickListener {
            mViewModel.isColorSelected.value = true
            mSelectedColor = mColors.get(position).code
            mViewModel.selectedColorCode.value = mSelectedColor
            notifyDataSetChanged()
        })
        if (mSelectedColor.equals(mColors.get(position).code)) {
            holder.background.setBackgroundResource(R.drawable.bg_frame_black)
            holder.frame.visibility = VISIBLE
        } else {
            holder.background.setBackgroundColor(Stylish.getAppContext().getColor(R.color.white_fafafa))
            holder.frame.visibility = INVISIBLE
        }
    }

    override fun getItemCount(): Int {
        return mColors.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image = itemView.findViewById<ImageView>(R.id.image_add_to_cart_color)
        var frame = itemView.findViewById<ImageView>(R.id.image_add_to_cart_color_white_frame)
        var background = itemView
    }
}