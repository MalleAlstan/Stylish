package com.john.stylish.ui.catalog.women

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
import com.john.stylish.databinding.FragWomenBinding
import com.john.stylish.model.objects.Product.Product
import com.john.stylish.ui.catalog.CatalogProductsAdapter
import com.john.stylish.ui.home.HotsListAdapter

import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.frag_home.*
import kotlinx.android.synthetic.main.frag_women.*

class FragWomen: Fragment(){

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
        showProductsWomenView()
    }

    private fun showProductsWomenView(){
        recyclerView_products_women.layoutManager = LinearLayoutManager(context)
        mProductsWomenDisposable = mFragWomenViewModel.getProductsWomen()
    }

    private fun setLiveDataObservers() {
        mProductsWomenObserver = Observer {
            mProductsWomenAdapter = CatalogProductsAdapter(it!!, activity!!, CatalogProductsAdapter.RecyclerViewType.LINEAR)
            recyclerView_products_women.adapter = mProductsWomenAdapter
        }
        mFragWomenViewModel.mWomenList.observe(this, mProductsWomenObserver)
    }

    private fun setDataBinding(inflater: LayoutInflater, container: ViewGroup?): View {
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