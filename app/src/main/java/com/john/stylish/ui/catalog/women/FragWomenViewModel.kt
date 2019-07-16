package com.john.stylish.ui.catalog.women

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.john.stylish.model.Repository.ProductRepository
import com.john.stylish.model.objects.Product.Product
import com.john.stylish.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import java.util.ArrayList

class FragWomenViewModel : ViewModel() {

    var isLoading = MutableLiveData<Boolean>()
    var mWomenList = MutableLiveData<ArrayList<Product>>()
    var mNextPage = 0
    var hasNexPage = true

    init {
        isLoading.value = false
        mWomenList.value = ArrayList()
    }

    fun getProductsWomen(): Disposable {
        val disposable = ProductRepository
            .getProductsWomen(mNextPage.toString())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    mWomenList.value = (mWomenList.value!! + it.data) as ArrayList<Product>
                    if (it.paging == 0) hasNexPage = false
                    if (hasNexPage) mNextPage = it.paging;
                },
                onError = { Log.d(Constants.TAG, it.toString())},
                onComplete = { Log.d(Constants.TAG, "Loading ProductWomen ok")}
            )
        return disposable
    }

    fun reset(){
        mWomenList.value = ArrayList()
        mNextPage = 0
        hasNexPage = true
    }
}