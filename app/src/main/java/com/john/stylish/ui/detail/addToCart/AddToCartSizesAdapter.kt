package com.john.stylish.ui.detail.addToCart

import android.arch.lifecycle.Observer
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.john.stylish.R
import com.john.stylish.Stylish

class AddToCartSizesAdapter(
    val mFragment: AddToCartDialog,
    val mSizes: ArrayList<String>,
    val mViewModel: AddToCartDialogViewModel
) : RecyclerView.Adapter<AddToCartSizesAdapter.ViewHolder>() {

    var mSelectedSize = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_add_to_cart_sizes, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.setText(mSizes.get(position))

        var enableObserver = Observer<Boolean> {
            if (it!!) {
                holder.text.alpha = 1.0f
                holder.image.alpha = 1.0f
                holder.image.setOnClickListener(View.OnClickListener {
                    mViewModel.isSizeSelected.value = true
                    mSelectedSize = mSizes.get(position)
                    mViewModel.selectedSize.value = mSelectedSize
                    notifyDataSetChanged()
                })
            }
        }
        mViewModel.isColorSelected.observe(mFragment, enableObserver)

        if (mSelectedSize.equals(mSizes.get(position))) {
            holder.background.setBackgroundResource(R.drawable.bg_frame_black)
            holder.frame.visibility = VISIBLE
        } else {
            holder.background.setBackgroundColor(Stylish.getAppContext().getColor(R.color.white_fafafa))
            holder.frame.visibility = INVISIBLE
        }
    }

    override fun getItemCount(): Int {
        return mSizes.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text = itemView.findViewById<TextView>(R.id.text_add_to_cart_size)
        var image = itemView.findViewById<ImageView>(R.id.image_add_to_cart_size)
        var frame = itemView.findViewById<ImageView>(R.id.image_add_to_cart_size_white_frame)
        var background = itemView
    }
}