package com.john.stylish.ui.catalog

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.john.stylish.R
import com.john.stylish.model.objects.Product.Product
import com.john.stylish.ui.MainViewModel

class CatalogProductsAdapter(
    private val mProductList: ArrayList<Product>,
    private val mContext: Context,
    private val mMainViewModel: MainViewModel
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val mInflator: LayoutInflater

    init {
        this.mInflator = LayoutInflater.from(mContext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_LINEAR -> LinearViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_catalog_linear, parent, false))
            TYPE_GRID -> GridViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_catalog_grid, parent, false))
            else -> LinearViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_catalog_linear, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LinearViewHolder -> bindLinearViewHolder(holder, mProductList[position])
            is GridViewHolder -> bindGridViewHolder(holder, mProductList[position])
        }
    }

    override fun getItemCount(): Int {
        return mProductList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (mMainViewModel.catalogType.value){
            MainViewModel.CATALOG_TYPE.LINEAR -> TYPE_LINEAR
            MainViewModel.CATALOG_TYPE.GRID -> TYPE_GRID
            else -> TYPE_LINEAR
        }
    }

    private fun bindLinearViewHolder(holder: LinearViewHolder, product: Product) {
        Glide.with(mContext).load(product.main_image).into(holder.imageMain)
        holder.textTitle.text = product.title
        holder.textPrice.text = product.price.toString() + "$"
        holder.itemView.findViewById<View>(R.id.layout_catalog_linear).setOnClickListener {
            mMainViewModel.fragType.value = MainViewModel.FRAG_TYPE.DETAIL
            mMainViewModel.detailProduct.value = product
        }
    }

    private fun bindGridViewHolder(holder: GridViewHolder, product: Product) {
        Glide.with(mContext).load(product.main_image).into(holder.imageMain)
        holder.textTitle.text = product.title
        holder.textPrice.text = product.price.toString() + "$"
        holder.itemView.findViewById<View>(R.id.layout_catalog_grid).setOnClickListener {
            mMainViewModel.fragType.value = MainViewModel.FRAG_TYPE.DETAIL
            mMainViewModel.detailProduct.value = product
        }
    }

    private inner class LinearViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageMain: ImageView = itemView.findViewById(R.id.image_catalog_linear_main)
        val textTitle: TextView = itemView.findViewById(R.id.text_catalog_linear_title)
        val textPrice: TextView = itemView.findViewById(R.id.text_catalog_linear_price)
    }

    private inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageMain: ImageView = itemView.findViewById(R.id.image_catalog_grid_main)
        val textTitle: TextView = itemView.findViewById(R.id.text_catalog_grid_title)
        val textPrice: TextView = itemView.findViewById(R.id.text_catalog_grid_price)
    }

    private inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {

        private const val TYPE_LINEAR = 0
        private const val TYPE_GRID   = 0x01
    }
}