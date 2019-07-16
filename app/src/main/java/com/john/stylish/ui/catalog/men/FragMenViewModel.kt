package com.john.stylish.ui.catalog.men

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.john.stylish.model.Repository.ProductRepository
import com.john.stylish.model.objects.Hots
import com.john.stylish.model.objects.Product.Product
import com.john.stylish.network.responses.HotsResponse
import com.john.stylish.utils.Constants
import com.john.stylish.utils.Constants.TAG
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import java.util.ArrayList

class FragMenViewModel : ViewModel() {

    var isLoading = MutableLiveData<Boolean>()
    var mMenList = MutableLiveData<ArrayList<Product>>() //MutableLiveData<ArrayList<Any>>()
    var mNextPage = 0
    var hasNexPage = true

    init {
        isLoading.value = false
        mMenList.value = ArrayList()
    }

    fun getProductsMen(): Disposable {
        val disposable = ProductRepository
            .getProductsMen(mNextPage.toString())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    mMenList.value = (mMenList.value!! + it.data) as ArrayList<Product>
                    if (it.paging == 0) hasNexPage = false
                    if (hasNexPage) mNextPage = it.paging;
                },
                onError = { Log.d(Constants.TAG, it.toString())},
                onComplete = { Log.d(Constants.TAG, "Loading ProductMen ok")}
            )
        return disposable
    }

    fun resetPaging(){
        mMenList.value = ArrayList()
        mNextPage = 0
        hasNexPage = true
    }
}