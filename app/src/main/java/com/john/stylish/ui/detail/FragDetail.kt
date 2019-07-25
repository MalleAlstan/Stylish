package com.john.stylish.ui.detail

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.john.stylish.databinding.FragDetailBinding
import kotlinx.android.synthetic.main.frag_detail.*
import android.support.v7.widget.PagerSnapHelper
import com.john.stylish.R
import com.john.stylish.model.objects.Product.Product
import com.john.stylish.ui.detail.addToCart.AddToCartDialog
import com.john.stylish.utils.Constants.KEY_SELECTED_PRODUCT


class FragDetail : Fragment(), View.OnClickListener {

    private lateinit var mFragDetailViewModel: FragDetailViewModel
    private lateinit var mFragDetailBinding: FragDetailBinding
    private var isShowingBottomSheet = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return setDataBinding(inflater, container)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        showProductDetailView()
    }

    private fun setDataBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        mFragDetailViewModel = ViewModelProviders.of(this).get(FragDetailViewModel::class.java)
        mFragDetailViewModel.selectedProduct.value = arguments?.getSerializable(KEY_SELECTED_PRODUCT) as Product;
        mFragDetailBinding = DataBindingUtil.inflate(inflater, R.layout.frag_detail, container, false)
        mFragDetailBinding.fragDetailViewModel = mFragDetailViewModel
        mFragDetailBinding.setLifecycleOwner(this)
        return mFragDetailBinding.root
    }

    private fun showProductDetailView() {
        mFragDetailViewModel.isLoading.value = true

        var product = mFragDetailViewModel.selectedProduct.value!!

        recyclerView_detail_images.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerView_detail_images.adapter = ProductImagesAdapter(activity!!, product.images)
        recyclerView_detail_images.scrollToPosition(1000)
        PagerSnapHelper().attachToRecyclerView(recyclerView_detail_images)

        recyclerView_detail_colors.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerView_detail_colors.adapter = ProductColorsAdapter(product.colors)

        button_detail_back.setOnClickListener(this)
        button_add_to_cart_detail.setOnClickListener(this)

        mFragDetailViewModel.isLoading.value = false
    }

    override fun onClick(v: View?) {
        when (v){
            button_detail_back -> activity!!.onBackPressed()
            button_add_to_cart_detail -> showBottomSheetDialog()
        }
    }

    private fun showBottomSheetDialog (){
        isShowingBottomSheet = true
        val addToCartDialog = AddToCartDialog.newInstance()
        var bundle = Bundle()
        bundle.putSerializable(KEY_SELECTED_PRODUCT, mFragDetailViewModel.selectedProduct.value)
        addToCartDialog.arguments = bundle
        addToCartDialog.show(activity!!.getSupportFragmentManager(), AddToCartDialog::class.java.getSimpleName())
    }
}