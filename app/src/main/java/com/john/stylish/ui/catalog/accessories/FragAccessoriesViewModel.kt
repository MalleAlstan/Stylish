package com.john.stylish.ui.catalog.accessories

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

class FragAccessoriesViewModel : ViewModel() {

    var isLoading = MutableLiveData<Boolean>()
    var mAccessoriesList= MutableLiveData<ArrayList<Product>>() //MutableLiveData<ArrayList<Any>>()
    var mNextPage = 0
    var hasNexPage = true

    init {
        isLoading.value = false
        mAccessoriesList.value = ArrayList()
    }

    fun getProductsAccessories(): Disposable {
        val disposable = ProductRepository
            .getProductsMen(mNextPage.toString())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    mAccessoriesList.value = (mAccessoriesList.value!! + it.data) as ArrayList<Product>
                    if (it.paging == 0) hasNexPage = false
                    if (hasNexPage) mNextPage = it.paging;
                },
                onError = { Log.d(Constants.TAG, it.toString())},
                onComplete = { Log.d(Constants.TAG, "Loading ProductAccessories ok")}
            )
        return disposable
    }

    fun resetPaging(){
        mAccessoriesList.value = ArrayList()
        mNextPage = 0
        hasNexPage = true
    }
}