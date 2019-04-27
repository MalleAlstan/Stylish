package com.john.stylish.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.view.View.OnClickListener
import com.john.stylish.R
import com.john.stylish.base.BaseActivity
import com.john.stylish.base.BaseInit
import com.john.stylish.databinding.ActivityMainBinding
import com.john.stylish.ui.cart.FragCart
import com.john.stylish.ui.catelog.FragCatalog
import com.john.stylish.ui.home.FragHome
import com.john.stylish.ui.profile.FragProfile
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), BaseInit, OnClickListener {

    lateinit var mMainViewModel: MainViewModel
    lateinit var mMainBinding: ActivityMainBinding
    lateinit var mFragmentObserver: Observer<MainViewModel.FRAG_TYPE>

    var mFragHome = FragHome()
    var mFragCatalog = FragCatalog()
    var mFragCart = FragCart()
    var mFragProfile = FragProfile()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    override fun init() {
        setTransparentStatusBar()
        setDataBinding()
        setToolbarPadding(app_toolbar)
        setOnClickListeners()
        setLiveDataObservers()
    }

    private fun setDataBinding() {
        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mMainBinding.mainViewModel = mMainViewModel
        mMainBinding.setLifecycleOwner(this)
    }

    private fun setLiveDataObservers() {
        mFragmentObserver = Observer { newFragType ->
           showFragment(newFragType)
        }
        mMainViewModel.fragType.observe(this, mFragmentObserver)
    }

    private fun setOnClickListeners() {
        btn_nav_home.setOnClickListener(this)
        btn_nav_catalog.setOnClickListener(this)
        btn_nav_cart.setOnClickListener(this)
        btn_nav_profile.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_nav_home -> mMainViewModel.fragType.value = MainViewModel.FRAG_TYPE.HOME
            R.id.btn_nav_catalog -> mMainViewModel.fragType.value = MainViewModel.FRAG_TYPE.CATALOG
            R.id.btn_nav_cart -> mMainViewModel.fragType.value = MainViewModel.FRAG_TYPE.CART
            R.id.btn_nav_profile -> mMainViewModel.fragType.value = MainViewModel.FRAG_TYPE.PROFILE
        }
    }

    private fun showFragment(fragType: MainViewModel.FRAG_TYPE?) {
        var fragment: Fragment? = null

        when (fragType) {
            MainViewModel.FRAG_TYPE.HOME -> fragment = mFragHome
            MainViewModel.FRAG_TYPE.CATALOG -> fragment = mFragCatalog
            MainViewModel.FRAG_TYPE.CART -> fragment = mFragCart
            MainViewModel.FRAG_TYPE.PROFILE -> fragment = mFragProfile
        }

        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_frag_container, fragment).commit()
        }
    }
}