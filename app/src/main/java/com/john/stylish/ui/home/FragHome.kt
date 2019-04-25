package com.john.stylish.ui.home

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.john.stylish.R
import com.john.stylish.databinding.FragHomeBinding
import com.john.stylish.model.responses.HotsResponse
import com.john.stylish.network.ApiServiceBuilder
import com.john.stylish.utils.Constants.Companion.TAG
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class FragHome: Fragment(){

    lateinit var mFragHomeViewModel: FragHomeViewModel
    lateinit var mFragHomeBinding: FragHomeBinding
    lateinit var mHotsListDisposable: Disposable

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return setDataBinding(inflater, container)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mFragHomeViewModel.getHotsList()
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