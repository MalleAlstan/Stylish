package com.john.stylish.ui.catalog.men

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.john.stylish.model.Repository.ProductRepository
import com.john.stylish.model.objects.Product.Product
import com.john.stylish.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import java.util.ArrayList

class FragMenViewModel : ViewModel() {

    var isLoading = MutableLiveData<Boolean>()
    var menList = MutableLiveData<ArrayList<Product>>() //MutableLiveData<ArrayList<Any>>()
    var nextPage = 0
    var hasNexPage = true

    init {
        isLoading.value = false
        menList.value = ArrayList()
    }

    fun getProductsMen(): Disposable {
        val disposable = ProductRepository
            .getProductsMen(nextPage.toString())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    menList.value = (menList.value!! + it.data) as ArrayList<Product>
                    if (it.paging == 0) hasNexPage = false
                    if (hasNexPage) nextPage = it.paging;
                },
                onError = { Log.d(Constants.TAG, it.toString())},
                onComplete = { Log.d(Constants.TAG, "Loading ProductMen ok")}
            )
        return disposable
    }

    fun reset(){
        menList.value = ArrayList()
        nextPage = 0
        hasNexPage = true
    }
}