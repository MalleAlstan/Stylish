package com.john.stylish.ui.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.john.stylish.R
import com.john.stylish.Stylish
import com.john.stylish.base.BaseFragment
import com.john.stylish.databinding.FragHomeBinding
import com.john.stylish.ui.MainViewModel
import com.john.stylish.ui.catalog.CatalogProductsAdapter
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.frag_home.*
import kotlinx.android.synthetic.main.frag_women.*
import kotlin.math.roundToInt

class FragHome: BaseFragment(){

    lateinit var mFragHomeViewModel: FragHomeViewModel
    lateinit var mFragHomeBinding: FragHomeBinding
    lateinit var mHotsListObserver: Observer<ArrayList<Any>>
    lateinit var mHotsListAdapter: HotsListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return setDataBinding(inflater, container)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setLiveDataObservers()
        showHotListsView()
    }

    private fun setDataBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        mFragHomeViewModel = ViewModelProviders.of(this).get(FragHomeViewModel::class.java)
        mFragHomeBinding = DataBindingUtil.inflate(inflater, R.layout.frag_home, container , false)
        mFragHomeBinding.fragHomeViewModel = mFragHomeViewModel
        mFragHomeBinding.setLifecycleOwner(activity)

        return mFragHomeBinding.root
    }

    private fun setLiveDataObservers() {
        mHotsListObserver = Observer {
            if (recyclerView_hot_list.adapter == null){
                mHotsListAdapter = HotsListAdapter(it!!, activity!!, mMainViewModel)
                recyclerView_hot_list.adapter = mHotsListAdapter
            } else mHotsListAdapter.updateData(it!!)

            mFragHomeViewModel.isLoading.value = false
            swipe_home.isRefreshing = false
        }
        mFragHomeViewModel.mHotsList.observe(this, mHotsListObserver)
    }

    private fun showHotListsView(){
        mFragHomeViewModel.isLoading.value = true
        recyclerView_hot_list.layoutManager = LinearLayoutManager(context)
        mFragHomeViewModel.getHotsList()

        swipe_home.setDistanceToTriggerSync(250)
        swipe_home.setProgressViewEndTarget(true, 150)
        swipe_home.setColorSchemeResources(R.color.colorAccent)
        swipe_home.setOnRefreshListener {
            mFragHomeViewModel.getHotsList()
        }
    }
}