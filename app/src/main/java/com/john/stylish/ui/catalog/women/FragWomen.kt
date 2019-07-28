package com.john.stylish.ui.catalog.women

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.john.stylish.R
import com.john.stylish.base.BaseCatalogFragment
import com.john.stylish.base.BaseFragment
import com.john.stylish.databinding.FragWomenBinding
import com.john.stylish.model.objects.Product.Product
import com.john.stylish.ui.MainViewModel
import com.john.stylish.ui.catalog.CatalogProductsAdapter
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.frag_women.*


class FragWomen: BaseCatalogFragment(){

    lateinit var mFragWomenViewModel: FragWomenViewModel
    lateinit var mFragWomenBinding: FragWomenBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return setDataBinding(inflater, container)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setCatalogView(mFragWomenViewModel, swipe_women, recyclerView_products_women)
    }

    private fun setDataBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        mFragWomenViewModel = ViewModelProviders.of(this).get(FragWomenViewModel::class.java)
        mFragWomenBinding = DataBindingUtil.inflate(inflater, R.layout.frag_women, container , false)
        mFragWomenBinding.fragWomenViewModel = mFragWomenViewModel
        mFragWomenBinding.setLifecycleOwner(activity)

        return mFragWomenBinding.root
    }
}