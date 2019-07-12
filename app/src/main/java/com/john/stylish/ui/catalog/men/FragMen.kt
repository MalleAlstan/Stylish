package com.john.stylish.ui.catalog.men

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
import com.john.stylish.databinding.FragMenBinding
import com.john.stylish.model.objects.Product.Product
import com.john.stylish.ui.catalog.CatalogProductsAdapter

import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.frag_men.*
import kotlinx.android.synthetic.main.frag_women.*

class FragMen: Fragment(){

    lateinit var mFragMenViewModel: FragMenViewModel
    lateinit var mFragMenBinding: FragMenBinding
    lateinit var mProductsMenDisposable: Disposable
    lateinit var mProductsMenObserver: Observer<ArrayList<Product>>
    lateinit var mProductsWomenAdapter: CatalogProductsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return setDataBinding(inflater, container)
        return null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setLiveDataObservers()
        showProductsMenView()
    }

    private fun showProductsMenView(){
        recyclerView_products_men.layoutManager = LinearLayoutManager(context)
        mProductsMenDisposable = mFragMenViewModel.getProductsMen()
    }

    private fun setLiveDataObservers() {
        mProductsMenObserver = Observer {
            mProductsWomenAdapter = CatalogProductsAdapter(it!!, activity!!, CatalogProductsAdapter.RecyclerViewType.LINEAR)
            recyclerView_products_men.adapter = mProductsWomenAdapter
        }
        mFragMenViewModel.mMenList.observe(this, mProductsMenObserver)
    }

    private fun setDataBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        mFragMenViewModel = ViewModelProviders.of(this).get(FragMenViewModel::class.java)
        mFragMenBinding = DataBindingUtil.inflate(inflater, R.layout.frag_men, container , false)
        mFragMenBinding.fragMenViewModel = mFragMenViewModel
        mFragMenBinding.setLifecycleOwner(activity)

        return mFragMenBinding.root
    }

    override fun onPause() {
        super.onPause()
        mProductsMenDisposable.dispose()
    }
}