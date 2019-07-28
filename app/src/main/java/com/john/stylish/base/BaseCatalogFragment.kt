package com.john.stylish.base

import android.arch.lifecycle.Observer
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.john.stylish.R
import com.john.stylish.model.objects.Product.Product
import com.john.stylish.ui.MainViewModel
import com.john.stylish.ui.catalog.CatalogProductsAdapter

open class BaseCatalogFragment : BaseFragment() {

    private lateinit var mLayoutTypeObserver: Observer<MainViewModel.CATALOG_TYPE>
    private lateinit var mFragViewModel: BaseCatalogViewModel
    private lateinit var mProductsListObserver: Observer<ArrayList<Product>>
    private lateinit var mProductsListAdapter: CatalogProductsAdapter
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
    private lateinit var mRecyclerView: RecyclerView

    fun setCatalogView(
        fragViewModel: BaseCatalogViewModel,
        swipeRefreshLayout: SwipeRefreshLayout,
        recyclerView: RecyclerView) {

        mSwipeRefreshLayout = swipeRefreshLayout
        mFragViewModel = fragViewModel
        mRecyclerView = recyclerView

        setLiveDataObservers()
    }

    private fun setLiveDataObservers() {
        mProductsListObserver = Observer {
            if (mRecyclerView.adapter == null) {
                mProductsListAdapter = CatalogProductsAdapter(it!!, requireActivity(), mMainViewModel)
                mRecyclerView.adapter = mProductsListAdapter
            } else mProductsListAdapter.updateData(it)
            mSwipeRefreshLayout.isRefreshing = false
        }
        mLayoutTypeObserver = Observer {
            mFragViewModel.reset()
            if (it == MainViewModel.CATALOG_TYPE.LINEAR) showProductRecyclerView(MainViewModel.CATALOG_TYPE.LINEAR)
            else showProductRecyclerView(MainViewModel.CATALOG_TYPE.GRID)
        }

        mFragViewModel.productList.observe(this, mProductsListObserver)
        mMainViewModel.catalogType.observe(this, mLayoutTypeObserver)
    }

    private fun showProductRecyclerView(catalogType: MainViewModel.CATALOG_TYPE) {

        val layoutManager = if (catalogType == MainViewModel.CATALOG_TYPE.LINEAR) LinearLayoutManager(context)
        else GridLayoutManager(context, 2)

        mRecyclerView.layoutManager = layoutManager
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = recyclerView!!.getChildCount()
                val totalItemCount = recyclerView.layoutManager.itemCount
                val firstVisibleItem =
                    (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                if (mFragViewModel.hasNexPage &&
                    mFragViewModel.isLoading.value != true &&
                    totalItemCount - visibleItemCount <= firstVisibleItem) {
                    mFragViewModel.getProductList()
                }
            }
        })

        mSwipeRefreshLayout.setDistanceToTriggerSync(250)
        mSwipeRefreshLayout.setProgressViewEndTarget(true, 150)
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent)
        mSwipeRefreshLayout.setOnRefreshListener {
            mFragViewModel.reset()
            mFragViewModel.getProductList()
        }
        mFragViewModel.getProductList()
        mFragViewModel.isLoading.value = true
    }
}