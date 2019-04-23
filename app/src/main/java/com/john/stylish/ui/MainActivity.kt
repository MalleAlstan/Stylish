package com.john.stylish.ui

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.view.WindowManager
import com.john.stylish.R
import com.john.stylish.base.BaseActivity
import com.john.stylish.base.BaseInit
import com.john.stylish.databinding.ActivityMainBinding
import com.john.stylish.utils.Constants.Companion.APP_NAME
import com.john.stylish.utils.Constants.Companion.FRAG_CART
import com.john.stylish.utils.Constants.Companion.FRAG_CATEGORY
import com.john.stylish.utils.Constants.Companion.FRAG_HOME
import com.john.stylish.utils.Constants.Companion.FRAG_PROFILE
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), BaseInit, OnClickListener{

    lateinit var mMainViewModel: MainViewModel
    lateinit var mMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    override fun init(){
        setTransparentStatusBar()
        setDataBinding()
        setToolbarPadding()
        setOnClickListeners()
    }

    private fun setDataBinding(){
        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mMainBinding.mainViewModel = mMainViewModel
        mMainBinding.setLifecycleOwner(this)
    }

    private fun setOnClickListeners(){
        btn_nav_home.setOnClickListener(this)
        btn_nav_category.setOnClickListener(this)
        btn_nav_cart.setOnClickListener(this)
        btn_nav_profile.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_nav_home -> setFragView(APP_NAME)
            R.id.btn_nav_category -> setFragView(FRAG_CATEGORY)
            R.id.btn_nav_cart -> setFragView(FRAG_CART)
            R.id.btn_nav_profile -> setFragView(FRAG_PROFILE)
        }
    }

    private fun setFragView(fragType: String){
        mMainViewModel.currentTitle.value = fragType
    }

    private fun setTransparentStatusBar() {
        val window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
    }

    private fun setToolbarPadding() {
        app_toolbar.setPadding(0, getStatusBarHeight(), 0, 0)
    }

    private fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) result = resources.getDimensionPixelSize(resourceId)
        return result
    }

}
