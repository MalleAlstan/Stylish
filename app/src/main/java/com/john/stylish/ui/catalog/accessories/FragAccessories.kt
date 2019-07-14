package com.john.stylish.ui.catalog.accessories

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
import com.john.stylish.databinding.FragAccessoriesBinding
import com.john.stylish.model.objects.Product.Product
import com.john.stylish.ui.MainViewModel
import com.john.stylish.ui.catalog.CatalogProductsAdapter
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.frag_accessories.*
import kotlinx.android.synthetic.main.frag_men.*
import kotlinx.android.synthetic.main.frag_women.*

class FragAccessories: Fragment(){

    lateinit var mMainViewModel: MainViewModel
    lateinit var mCatalogTypeObserver: Observer<MainViewModel.CATALOG_TYPE>
    lateinit var mFragAccessoriesViewModel: FragAccessoriesViewModel
    lateinit var mFragAccessoriesBinding: FragAccessoriesBinding
    lateinit var mProductsAccessoriesDisposable: Disposable
    lateinit var mProductsAccessoriesObserver: Observer<ArrayList<Product>>
    lateinit var mProductsWomenAdapter: CatalogProductsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return setDataBinding(inflater, container)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setLiveDataObservers()
        showProductsAccessoriesView(MainViewModel.CATALOG_TYPE.LINEAR)
    }

    private fun showProductsAccessoriesView(type: MainViewModel.CATALOG_TYPE){
        mFragAccessoriesViewModel.isLoading.value = true
        if (type == MainViewModel.CATALOG_TYPE.LINEAR) recyclerView_products_accessories.layoutManager = LinearLayoutManager(context)
        else recyclerView_products_accessories.layoutManager = GridLayoutManager(context, 2)
        mProductsAccessoriesDisposable = mFragAccessoriesViewModel.getProductsAccessories()

        swipe_accessories.setDistanceToTriggerSync(250)
        swipe_accessories.setProgressViewEndTarget(true, 150)
        swipe_accessories.setColorSchemeResources(R.color.colorAccent)
        swipe_accessories.setOnRefreshListener {
            mProductsAccessoriesDisposable = mFragAccessoriesViewModel.getProductsAccessories()
        }
    }

    private fun setLiveDataObservers() {
        mProductsAccessoriesObserver = Observer {
            mProductsWomenAdapter = CatalogProductsAdapter(it!!, activity!!, mMainViewModel)
            recyclerView_products_accessories.adapter = mProductsWomenAdapter
            mFragAccessoriesViewModel.isLoading.value = false
            swipe_accessories.isRefreshing = false
        }
        mFragAccessoriesViewModel.mAccessoriesList.observe(this, mProductsAccessoriesObserver)

        mCatalogTypeObserver = Observer {
            if (it == MainViewModel.CATALOG_TYPE.LINEAR) showProductsAccessoriesView(MainViewModel.CATALOG_TYPE.LINEAR)
            else showProductsAccessoriesView(MainViewModel.CATALOG_TYPE.GRID)
        }
        mMainViewModel.catalogType.observe(this, mCatalogTypeObserver)
    }

    private fun setDataBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        mMainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        mFragAccessoriesViewModel = ViewModelProviders.of(this).get(FragAccessoriesViewModel::class.java)
        mFragAccessoriesBinding = DataBindingUtil.inflate(inflater, R.layout.frag_accessories, container , false)
        mFragAccessoriesBinding.fragAccessoriesViewModel = mFragAccessoriesViewModel
        mFragAccessoriesBinding.setLifecycleOwner(activity)

        return mFragAccessoriesBinding.root
    }

    override fun onPause() {
        super.onPause()
        mProductsAccessoriesDisposable.dispose()
    }
}