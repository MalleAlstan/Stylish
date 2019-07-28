package com.john.stylish.ui.catalog.men

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
import com.john.stylish.databinding.FragMenBinding
import com.john.stylish.model.objects.Product.Product
import com.john.stylish.ui.MainViewModel
import com.john.stylish.ui.catalog.CatalogProductsAdapter

import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.frag_accessories.*
import kotlinx.android.synthetic.main.frag_men.*

class FragMen: BaseCatalogFragment(){

    lateinit var mFragMenViewModel: FragMenViewModel
    lateinit var mFragMenBinding: FragMenBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return setDataBinding(inflater, container)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setCatalogView(mFragMenViewModel, swipe_men, recyclerView_products_men)
    }

    private fun setDataBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        mFragMenViewModel = ViewModelProviders.of(this).get(FragMenViewModel::class.java)
        mFragMenBinding = DataBindingUtil.inflate(inflater, R.layout.frag_men, container , false)
        mFragMenBinding.fragMenViewModel = mFragMenViewModel
        mFragMenBinding.setLifecycleOwner(activity)

        return mFragMenBinding.root
    }
}