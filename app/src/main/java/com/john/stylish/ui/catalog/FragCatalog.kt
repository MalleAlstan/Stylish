package com.john.stylish.ui.catalog

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.john.stylish.R
import com.john.stylish.Stylish
import com.john.stylish.databinding.FragCatalogBinding
import kotlinx.android.synthetic.main.frag_catalog.*
import kotlinx.android.synthetic.main.frag_home.*

class FragCatalog: Fragment(){

    lateinit var mFragCatalogViewModel: FragCatalogViewModel
    lateinit var mFragCatalogBinding: FragCatalogBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return setDataBinding(inflater, container)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initCatalogView()
    }

    private fun initCatalogView(){
        pager_catalog.offscreenPageLimit = 3
        pager_catalog.adapter = CatalogPageAdapter(Stylish.getAppContext(), activity!!.supportFragmentManager)
        tab_catalog.setupWithViewPager(pager_catalog)
    }

    private fun setDataBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        mFragCatalogViewModel = ViewModelProviders.of(this).get(FragCatalogViewModel::class.java)
        mFragCatalogBinding = DataBindingUtil.inflate(inflater, R.layout.frag_catalog, container , false)
        mFragCatalogBinding.setLifecycleOwner(activity)

        return mFragCatalogBinding.root
    }
}