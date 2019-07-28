package com.john.stylish.ui.catalog.accessories

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.john.stylish.R
import com.john.stylish.base.BaseCatalogFragment
import com.john.stylish.databinding.FragAccessoriesBinding
import com.john.stylish.model.objects.Product.Product
import com.john.stylish.ui.MainViewModel
import com.john.stylish.ui.catalog.CatalogProductsAdapter
import kotlinx.android.synthetic.main.frag_accessories.*

class FragAccessories: BaseCatalogFragment(){

    lateinit var mFragAccessoriesViewModel: FragAccessoriesViewModel
    lateinit var mFragAccessoriesBinding: FragAccessoriesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return setDataBinding(inflater, container)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setCatalogView(mFragAccessoriesViewModel, swipe_accessories, recyclerView_products_accessories)
    }

    private fun setDataBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        mFragAccessoriesViewModel = ViewModelProviders.of(this).get(FragAccessoriesViewModel::class.java)
        mFragAccessoriesBinding = DataBindingUtil.inflate(inflater, R.layout.frag_accessories, container , false)
        mFragAccessoriesBinding.fragAccessoriesViewModel = mFragAccessoriesViewModel
        mFragAccessoriesBinding.setLifecycleOwner(activity)

        return mFragAccessoriesBinding.root
    }
}