package com.john.stylish.ui.home

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.john.stylish.R
import com.john.stylish.databinding.FragHomeBinding

class FragHome: Fragment(){

    lateinit var mHomeViewModel: HomeViewModel
    lateinit var mFragHomeBinding: FragHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return setDataBinding(inflater, container)
    }

    private fun setDataBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        mHomeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        mFragHomeBinding = DataBindingUtil.inflate(inflater, R.layout.frag_home, container , false)
        mFragHomeBinding.homeViewModel = mHomeViewModel
        mFragHomeBinding.setLifecycleOwner(this)

        var root : View  = mFragHomeBinding.root

        return root
    }
}