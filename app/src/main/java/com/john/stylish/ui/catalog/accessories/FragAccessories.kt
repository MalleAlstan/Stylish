package com.john.stylish.ui.catalog.accessories

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.john.stylish.R
import com.john.stylish.databinding.FragAccessoriesBinding
import com.john.stylish.databinding.FragMenBinding
import com.john.stylish.ui.catalog.men.FragMenViewModel
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.frag_accessories.*

class FragAccessories: Fragment(){

    lateinit var mFragAccessoriesViewModel: FragAccessoriesViewModel
    lateinit var mFragAccessoriesBinding: FragAccessoriesBinding
    lateinit var mProductsAccessoriesDisposable: Disposable
    lateinit var mProductsAccessoriesObserver: Observer<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return setDataBinding(inflater, container)
        return null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setLiveDataObservers()
        showProductsAccessoriesView()
    }

    private fun showProductsAccessoriesView(){
        mProductsAccessoriesDisposable = mFragAccessoriesViewModel.getProductsAccessories()
    }

    private fun setLiveDataObservers() {
        mProductsAccessoriesObserver = Observer {
            products_accessories.setText(it)
        }
        mFragAccessoriesViewModel.mAccessoriesList.observe(this, mProductsAccessoriesObserver)
    }

    private fun setDataBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        mFragAccessoriesViewModel = ViewModelProviders.of(this).get(FragAccessoriesViewModel::class.java)
        mFragAccessoriesBinding = DataBindingUtil.inflate(inflater, R.layout.frag_accessories, container , false)
        mFragAccessoriesBinding.fragAccessoriesViewModel = mFragAccessoriesViewModel
        mFragAccessoriesBinding.setLifecycleOwner(activity)

        return mFragAccessoriesBinding.root
    }

    override fun onPause() {
        super.onPause()
        mProductsAccessoriesDisposable.dispose()
    }
}