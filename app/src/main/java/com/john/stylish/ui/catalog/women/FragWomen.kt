package com.john.stylish.ui.catalog.women

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.john.stylish.R
import com.john.stylish.databinding.FragWomenBinding
import com.john.stylish.model.objects.Product.Product
import com.john.stylish.ui.MainViewModel
import com.john.stylish.ui.catalog.CatalogProductsAdapter
import com.john.stylish.ui.catalog.men.FragMenViewModel

import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.frag_men.*
import kotlinx.android.synthetic.main.frag_women.*

class FragWomen: Fragment(){

    lateinit var mMainViewModel: MainViewModel
    lateinit var mCatalogTypeObserver: Observer<MainViewModel.CATALOG_TYPE>
    lateinit var mFragWomenViewModel: FragWomenViewModel
    lateinit var mFragWomenBinding: FragWomenBinding
    lateinit var mProductsWomenDisposable: Disposable
    lateinit var mProductsWomenObserver: Observer<ArrayList<Product>>
    lateinit var mProductsWomenAdapter: CatalogProductsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return setDataBinding(inflater, container)
        return null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setLiveDataObservers()
    }

    private fun showProductsMenView(type: MainViewModel.CATALOG_TYPE){
        if (type == MainViewModel.CATALOG_TYPE.LINEAR) recyclerView_products_women.layoutManager = LinearLayoutManager(context)
        else recyclerView_products_women.layoutManager = GridLayoutManager(context, 2)

        mProductsWomenDisposable = mFragWomenViewModel.getProductsWomen()
    }

    private fun setLiveDataObservers() {
        mProductsWomenObserver = Observer {
            mProductsWomenAdapter = CatalogProductsAdapter(it!!, activity!!, mMainViewModel)
            recyclerView_products_women.adapter = mProductsWomenAdapter
        }
        mFragWomenViewModel.mWomenList.observe(this, mProductsWomenObserver)

        mCatalogTypeObserver = Observer {
            if (it == MainViewModel.CATALOG_TYPE.LINEAR) showProductsMenView(MainViewModel.CATALOG_TYPE.LINEAR)
            else showProductsMenView(MainViewModel.CATALOG_TYPE.GRID)

            Log.d("123456", it.toString())
        }
        mMainViewModel.catalogType.observe(this, mCatalogTypeObserver)
    }

    private fun setDataBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        mMainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        mFragWomenViewModel = ViewModelProviders.of(this).get(FragWomenViewModel::class.java)
        mFragWomenBinding = DataBindingUtil.inflate(inflater, R.layout.frag_women, container , false)
        mFragWomenBinding.fragWomenViewModel = mFragWomenViewModel
        mFragWomenBinding.setLifecycleOwner(activity)

        return mFragWomenBinding.root
    }

    override fun onPause() {
        super.onPause()
        mProductsWomenDisposable.dispose()
    }
}