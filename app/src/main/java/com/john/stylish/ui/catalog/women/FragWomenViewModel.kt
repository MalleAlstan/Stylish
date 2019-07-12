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
    var mWomenList= MutableLiveData<ArrayList<Product>>() //MutableLiveData<ArrayList<Any>>()

    init {
        isLoading.value = false
    }

    fun getProductsWomen(): Disposable {

        isLoading.value = true

        val disposable = ProductRepository
            .getProductsWomen()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {setWomenList(it.data); mWomenList.value = it.data},
                onError = { Log.d(Constants.TAG, it.toString()); isLoading.value = false },
                onComplete = { Log.d(Constants.TAG, "Loading ProductWomen ok"); isLoading.value = false}
            )
        return disposable
    }

    private fun setWomenList(productsMen: ArrayList<Product>){
        productsMen.toObservable()
            .subscribeBy (
                onNext = {
                    Log.d (Constants.TAG, it.title)
                    Log.d (Constants.TAG, it.note)
                    Log.d (Constants.TAG, it.main_image)
                },
                onError = { Log.d(Constants.TAG, it.toString()); isLoading.value = false },
                onComplete = { Log.d(Constants.TAG, "Loading ProductWomen ok"); isLoading.value = false}
            )
    }
}