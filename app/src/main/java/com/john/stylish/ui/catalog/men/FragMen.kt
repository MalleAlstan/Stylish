package com.john.stylish.ui.catalog.men

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
import com.john.stylish.Stylish
import com.john.stylish.databinding.FragMenBinding
import com.john.stylish.model.objects.Product.Product
import com.john.stylish.ui.MainViewModel
import com.john.stylish.ui.catalog.CatalogProductsAdapter

import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.frag_men.*
import kotlinx.android.synthetic.main.frag_women.*

class FragMen: Fragment(){

    lateinit var mMainViewModel: MainViewModel
    lateinit var mCatalogTypeObserver: Observer<MainViewModel.CATALOG_TYPE>
    lateinit var mFragMenViewModel: FragMenViewModel
    lateinit var mProductsMenObserver: Observer<ArrayList<Product>>
    lateinit var mFragMenBinding: FragMenBinding
    lateinit var mProductsMenDisposable: Disposable
    lateinit var mProductsWomenAdapter: CatalogProductsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return setDataBinding(inflater, container)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setLiveDataObservers()
        showProductsMenView(MainViewModel.CATALOG_TYPE.LINEAR)
    }

    private fun showProductsMenView(type: MainViewModel.CATALOG_TYPE){
        mFragMenViewModel.isLoading.value = true
        if (type == MainViewModel.CATALOG_TYPE.LINEAR) recyclerView_products_men.layoutManager = LinearLayoutManager(context)
        else recyclerView_products_men.layoutManager = GridLayoutManager(context, 2)
        mProductsMenDisposable = mFragMenViewModel.getProductsMen()

        swipe_men.setDistanceToTriggerSync(250)
        swipe_men.setProgressViewEndTarget(true, 150)
        swipe_men.setColorSchemeResources(R.color.colorAccent)
        swipe_men.setOnRefreshListener {
            mProductsMenDisposable = mFragMenViewModel.getProductsMen()
        }
    }

    private fun setLiveDataObservers() {
        mProductsMenObserver = Observer {
            mProductsWomenAdapter = CatalogProductsAdapter(it!!, activity!!, mMainViewModel)
            recyclerView_products_men.adapter = mProductsWomenAdapter
            mFragMenViewModel.isLoading.value = false
            swipe_men.isRefreshing = false
        }
        mFragMenViewModel.mMenList.observe(this, mProductsMenObserver)

        mCatalogTypeObserver = Observer {
            if (it == MainViewModel.CATALOG_TYPE.LINEAR) showProductsMenView(MainViewModel.CATALOG_TYPE.LINEAR)
            else showProductsMenView(MainViewModel.CATALOG_TYPE.GRID)
        }
        mMainViewModel.catalogType.observe(this, mCatalogTypeObserver)
    }

    private fun setDataBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        mMainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        mFragMenViewModel = ViewModelProviders.of(this).get(FragMenViewModel::class.java)
        mFragMenBinding = DataBindingUtil.inflate(inflater, R.layout.frag_men, container , false)
        mFragMenBinding.fragMenViewModel = mFragMenViewModel
        mFragMenBinding.setLifecycleOwner(activity)

        return mFragMenBinding.root
    }

    override fun onPause() {
        super.onPause()
        if (mProductsMenDisposable != null )mProductsMenDisposable.dispose()
    }
}