package com.john.stylish.ui.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.john.stylish.R
import com.john.stylish.databinding.FragDetailBinding
import com.john.stylish.model.objects.Product.Product
import com.john.stylish.ui.MainViewModel
import kotlinx.android.synthetic.main.frag_detail.*
import android.support.v7.widget.PagerSnapHelper


class FragDetail : Fragment(), View.OnClickListener {

    private lateinit var mMainViewModel: MainViewModel
    private lateinit var mFragDetailViewModel: FragDetailViewModel
    private lateinit var mFragDetailBinding: FragDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return setDataBinding(inflater, container)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        showProductDetailView()
    }

    private fun setDataBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        mMainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        mFragDetailViewModel = ViewModelProviders.of(this).get(FragDetailViewModel::class.java)
        mFragDetailBinding = DataBindingUtil.inflate(inflater, R.layout.frag_detail, container, false)
        mFragDetailBinding.fragDetailViewModel = mFragDetailViewModel
        mFragDetailBinding.setLifecycleOwner(activity)
        return mFragDetailBinding.root
    }

    private fun showProductDetailView() {
        mFragDetailViewModel.isLoading.value = true

        var product = mMainViewModel.detailProduct.value!!

        text_detail_title.text = product.title
        text_detail_price.text = "NT$" + product.price.toString()
        text_detail_id.text = product.id.toString()
        text_detail_story.text = product.story
        if (product.sizes.size > 1)text_detail_sizes.text = product.sizes[0] + " - " + product.sizes[product.sizes.size - 1]
        else text_detail_sizes.text = product.sizes[0]
        text_detail_variants.text = product.variants.size.toString()
        text_detail_texture.text = product.texture
        text_detail_wash.text = product.wash
        text_detail_place.text = product.place
        text_detail_note.text = product.note

        recyclerView_detail_images.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerView_detail_images.adapter = ProductImagesAdapter(activity!!, product.images)
        PagerSnapHelper().attachToRecyclerView(recyclerView_detail_images)

        recyclerView_detail_colors.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerView_detail_colors.adapter = ProductColorsAdapter(product.colors)
        recyclerView_detail_images.scrollToPosition(1000)

        button_detail_back.setOnClickListener(this)

        mFragDetailViewModel.isLoading.value = false
    }

    override fun onClick(v: View?) {
        when (v){
            button_detail_back -> activity!!.onBackPressed()
        }
    }
}