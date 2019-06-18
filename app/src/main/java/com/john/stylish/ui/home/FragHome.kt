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
import com.john.stylish.databinding.FragHomeBinding
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.frag_home.*

class FragHome: Fragment(){

    lateinit var mFragHomeViewModel: FragHomeViewModel
    lateinit var mFragHomeBinding: FragHomeBinding
    lateinit var mHotsListDisposable: Disposable
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

    private fun showHotListsView(){
        recyclerView_hot_list.layoutManager = LinearLayoutManager(context)
        mHotsListDisposable = mFragHomeViewModel.getHotsList()
    }

    private fun setLiveDataObservers() {
        mHotsListObserver = Observer {
            mHotsListAdapter = HotsListAdapter(it!!, activity!!)
            recyclerView_hot_list.adapter = mHotsListAdapter
        }
        mFragHomeViewModel.mHotsList.observe(this, mHotsListObserver)
    }

    private fun setDataBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        mFragHomeViewModel = ViewModelProviders.of(this).get(FragHomeViewModel::class.java)
        mFragHomeBinding = DataBindingUtil.inflate(inflater, R.layout.frag_home, container , false)
        mFragHomeBinding.fragHomeViewModel = mFragHomeViewModel
        mFragHomeBinding.setLifecycleOwner(activity)

        return mFragHomeBinding.root
    }

    override fun onPause() {
        super.onPause()
        mHotsListDisposable.dispose()
    }
}