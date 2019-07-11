package com.john.stylish.ui.catalog.men

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.john.stylish.R
import com.john.stylish.databinding.FragMenBinding

import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.frag_men.*

class FragMen: Fragment(){

    lateinit var mFragMenViewModel: FragMenViewModel
    lateinit var mFragMenBinding: FragMenBinding
    lateinit var mProductsMenDisposable: Disposable
    lateinit var mProductsMenObserver: Observer<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return setDataBinding(inflater, container)
        return null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setLiveDataObservers()
        showProductsMenView()
    }

    private fun showProductsMenView(){
        mProductsMenDisposable = mFragMenViewModel.getProductsMen()
    }

    private fun setLiveDataObservers() {
        mProductsMenObserver = Observer {
           products_men.setText(it)
        }
        mFragMenViewModel.mMenList.observe(this, mProductsMenObserver)
    }

    private fun setDataBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        mFragMenViewModel = ViewModelProviders.of(this).get(FragMenViewModel::class.java)
        mFragMenBinding = DataBindingUtil.inflate(inflater, R.layout.frag_men, container , false)
        mFragMenBinding.fragMenViewModel = mFragMenViewModel
        mFragMenBinding.setLifecycleOwner(activity)

        return mFragMenBinding.root
    }

    override fun onPause() {
        super.onPause()
        mProductsMenDisposable.dispose()
    }
}