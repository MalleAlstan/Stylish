package com.john.stylish.base

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import com.john.stylish.ui.MainViewModel

abstract class BaseFragment : Fragment() {

    lateinit var mMainViewModel: MainViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mMainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
    }
}