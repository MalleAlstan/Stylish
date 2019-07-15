package com.john.stylish.ui.detail

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.john.stylish.R
import com.john.stylish.databinding.FragDetailBinding
import com.john.stylish.databinding.FragHomeBinding

class FragDetail : Fragment() {

    lateinit var mFragDetailViewModel: FragDetailViewModel
    lateinit var mFragDetailBinding: FragDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return setDataBinding(inflater, container)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setLiveDataObservers()
        showProductDetailView()
    }

    private fun showProductDetailView() {
    }

    private fun setLiveDataObservers() {
    }

    private fun setDataBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        mFragDetailViewModel = ViewModelProviders.of(this).get(FragDetailViewModel::class.java)
        mFragDetailBinding = DataBindingUtil.inflate(inflater, R.layout.frag_detail, container, false)
        mFragDetailBinding.fragDetailViewModel = mFragDetailViewModel
        mFragDetailBinding.setLifecycleOwner(activity)

        return mFragDetailBinding.root
    }

    override fun onPause() {
        super.onPause()
    }
}