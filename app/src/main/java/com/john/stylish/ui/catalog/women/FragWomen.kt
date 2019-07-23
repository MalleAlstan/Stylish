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
import com.john.stylish.databinding.FragWomenBinding
import com.john.stylish.model.objects.Product.Product
import com.john.stylish.ui.MainViewModel
import com.john.stylish.ui.catalog.CatalogProductsAdapter
import io.reactivex.disposables.Disposable
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
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setLiveDataObservers()
    }

    override fun onPause() {
        super.onPause()
        mProductsWomenDisposable.dispose()
    }

    private fun setDataBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        mMainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        mFragWomenViewModel = ViewModelProviders.of(this).get(FragWomenViewModel::class.java)
        mFragWomenBinding = DataBindingUtil.inflate(inflater, R.layout.frag_women, container , false)
        mFragWomenBinding.fragWomenViewModel = mFragWomenViewModel
        mFragWomenBinding.setLifecycleOwner(activity)

        return mFragWomenBinding.root
    }

    private fun setLiveDataObservers() {
        mProductsWomenObserver = Observer {
            if (recyclerView_products_women.adapter == null){
                mProductsWomenAdapter = CatalogProductsAdapter(it!!, activity!!, mMainViewModel)
                recyclerView_products_women.adapter = mProductsWomenAdapter
            } else mProductsWomenAdapter.updateData(it!!)

            mFragWomenViewModel.isLoading.value = false
            swipe_women.isRefreshing = false
        }
        mFragWomenViewModel.womenList.observe(this, mProductsWomenObserver)

        mCatalogTypeObserver = Observer {
            mFragWomenViewModel.reset()
            if (it == MainViewModel.CATALOG_TYPE.LINEAR) showProductsWomenView(MainViewModel.CATALOG_TYPE.LINEAR)
            else showProductsWomenView(MainViewModel.CATALOG_TYPE.GRID)
        }
        mMainViewModel.catalogType.observe(this, mCatalogTypeObserver)
    }

    private fun showProductsWomenView(type: MainViewModel.CATALOG_TYPE){

        mFragWomenViewModel.isLoading.value = true

        if (type == MainViewModel.CATALOG_TYPE.LINEAR) recyclerView_products_women.layoutManager = LinearLayoutManager(context)
        else recyclerView_products_women.layoutManager = GridLayoutManager(context, 2)

        recyclerView_products_women.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = recyclerView!!.getChildCount()
                val totalItemCount = recyclerView.getLayoutManager()!!.itemCount
                val firstVisibleItem = (recyclerView.getLayoutManager() as LinearLayoutManager).findFirstVisibleItemPosition()

                if ( mFragWomenViewModel.hasNexPage &&
                    mFragWomenViewModel.isLoading.value != true &&
                    totalItemCount - visibleItemCount <= firstVisibleItem) {

                    mFragWomenViewModel.isLoading.value = true
                    mProductsWomenDisposable = mFragWomenViewModel.getProductsWomen()
                }
            }
        })

        swipe_women.setDistanceToTriggerSync(250)
        swipe_women.setProgressViewEndTarget(true, 150)
        swipe_women.setColorSchemeResources(R.color.colorAccent)
        swipe_women.setOnRefreshListener {
            mFragWomenViewModel.reset()
            mProductsWomenDisposable = mFragWomenViewModel.getProductsWomen()
        }

        mProductsWomenDisposable = mFragWomenViewModel.getProductsWomen()
    }
}