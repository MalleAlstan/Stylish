package com.john.stylish.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import com.john.stylish.R
import com.john.stylish.base.BaseActivity
import com.john.stylish.base.BaseInit
import com.john.stylish.databinding.ActivityMainBinding
import com.john.stylish.model.objects.Product.Product
import com.john.stylish.ui.cart.FragCart
import com.john.stylish.ui.catalog.FragCatalog
import com.john.stylish.ui.detail.FragDetail
import com.john.stylish.ui.home.FragHome
import com.john.stylish.ui.profile.FragProfile
import com.john.stylish.utils.Constants.KEY_SELECTED_PRODUCT
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), BaseInit, OnClickListener {

    lateinit var mMainViewModel: MainViewModel
    lateinit var mMainBinding: ActivityMainBinding
    lateinit var mFragmentObserver: Observer<MainViewModel.FRAG_TYPE>
    lateinit var mSelectedProductObserver: Observer<Product>

    companion object {
        lateinit var mFragHome: FragHome
        lateinit var mFragCatalog: FragCatalog
        lateinit var mFragCart: FragCart
        lateinit var mFragProfile: FragProfile
        lateinit var mFragDetail: FragDetail

        fun isHomeInit() = :: mFragHome.isInitialized
        fun isCatalogInit() = :: mFragCatalog.isInitialized
        fun isCartInit() = :: mFragCart.isInitialized
        fun isProfileInit() = :: mFragProfile.isInitialized
        fun isDetailInit() = :: mFragDetail.isInitialized
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    override fun init() {
        setTransparentStatusBar()
        setDataBinding()
        setLiveDataObservers()
        setOnClickListeners()
        setToolbarPadding(app_toolbar)
    }

    private fun setDataBinding() {
        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mMainBinding.mainViewModel = mMainViewModel
        mMainBinding.setLifecycleOwner(this)
    }

    private fun setLiveDataObservers() {
        mFragmentObserver = Observer { newFragType ->
            transFragment(newFragType)
        }
        mSelectedProductObserver = Observer { product ->
            mMainViewModel.fragType.value = MainViewModel.FRAG_TYPE.DETAIL
        }

        mMainViewModel.fragType.observe(this, mFragmentObserver)
        mMainViewModel.selectedProduct.observe(this, mSelectedProductObserver)
    }

    private fun setOnClickListeners() {
        btn_nav_home.setOnClickListener(this)
        btn_nav_catalog.setOnClickListener(this)
        btn_nav_cart.setOnClickListener(this)
        btn_nav_profile.setOnClickListener(this)
        catalog_type.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_nav_home -> mMainViewModel.fragType.value = MainViewModel.FRAG_TYPE.HOME
            R.id.btn_nav_catalog -> mMainViewModel.fragType.value = MainViewModel.FRAG_TYPE.CATALOG
            R.id.btn_nav_cart -> mMainViewModel.fragType.value = MainViewModel.FRAG_TYPE.CART
            R.id.btn_nav_profile -> mMainViewModel.fragType.value = MainViewModel.FRAG_TYPE.PROFILE
            R.id.catalog_type -> changeCatalogType()
        }
    }

    private fun changeCatalogType(){
        if (mMainViewModel.catalogType.value == MainViewModel.CATALOG_TYPE.LINEAR)
            mMainViewModel.catalogType.value = MainViewModel.CATALOG_TYPE.GRID
        else mMainViewModel.catalogType.value = MainViewModel.CATALOG_TYPE.LINEAR
    }

    private fun transFragment(fragType: MainViewModel.FRAG_TYPE?) {
        if (fragType != MainViewModel.FRAG_TYPE.DETAIL) mMainViewModel.lastFragType = mMainViewModel.fragType.value!!
        val transaction = supportFragmentManager.beginTransaction()
        when (fragType) {
            MainViewModel.FRAG_TYPE.HOME -> showFragHomeView(transaction)
            MainViewModel.FRAG_TYPE.CATALOG -> showFragCatalogView(transaction)
            MainViewModel.FRAG_TYPE.CART -> showFragCartView(transaction)
            MainViewModel.FRAG_TYPE.PROFILE -> showFragProfileView(transaction)
            MainViewModel.FRAG_TYPE.DETAIL -> showFragDetailView(transaction)
        }
    }

    private fun showFragDetailView(transaction: FragmentTransaction){
        mFragDetail = FragDetail()
        var bundle = Bundle()
        bundle.putSerializable(KEY_SELECTED_PRODUCT, mMainViewModel.selectedProduct.value)
        mFragDetail.arguments = bundle
        transaction.add(R.id.frame_frag_container, mFragDetail)
        transaction.commit()
    }

    private fun showFragHomeView(transaction: FragmentTransaction){
        if (!isHomeInit()) {
            mFragHome = FragHome()
            transaction.add(R.id.frame_frag_container, mFragHome)
        } else transaction.show(mFragHome)
        if (isCatalogInit()) transaction.hide(mFragCatalog)
        if (isCartInit()) transaction.hide(mFragCart)
        if (isProfileInit()) transaction.hide(mFragProfile)
        transaction.commit()
    }

    private fun showFragCatalogView(transaction: FragmentTransaction){
        if (!isCatalogInit()) {
            mFragCatalog = FragCatalog()
            transaction.add(R.id.frame_frag_container, mFragCatalog)
        } else transaction.show(mFragCatalog)
        if (isHomeInit()) transaction.hide(mFragHome)
        if (isCartInit()) transaction.hide(mFragCart)
        if (isProfileInit()) transaction.hide(mFragProfile)
        transaction.commit()
    }

    private fun showFragCartView(transaction: FragmentTransaction){
        if (!isCartInit()) {
            mFragCart = FragCart()
            transaction.add(R.id.frame_frag_container, mFragCart)
        } else transaction.show(mFragCart)
        if (isHomeInit()) transaction.hide(mFragHome)
        if (isCatalogInit()) transaction.hide(mFragCatalog)
        if (isProfileInit()) transaction.hide(mFragProfile)
        transaction.commit()
    }

    private fun showFragProfileView(transaction: FragmentTransaction){
        if (!isProfileInit()) {
            mFragProfile = FragProfile()
            transaction.add(R.id.frame_frag_container, mFragProfile)
        } else transaction.show(mFragProfile)
        if (isHomeInit()) transaction.hide(mFragHome)
        if (isCatalogInit()) transaction.hide(mFragCatalog)
        if (isCartInit()) transaction.hide(mFragCart)
        transaction.commit()
    }

    private fun perFormOnBackPressed(){
        if (isDetailInit() && mFragDetail.isAdded) {
            supportFragmentManager.beginTransaction().remove(mFragDetail).commit()
            mMainViewModel.fragType.value = mMainViewModel.lastFragType
        } else super.onBackPressed()
    }

    override fun onBackPressed() {
        perFormOnBackPressed()
    }
}