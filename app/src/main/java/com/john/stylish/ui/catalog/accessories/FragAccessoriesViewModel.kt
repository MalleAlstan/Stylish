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

    init {
        isLoading.value = false
    }

    fun getProductsAccessories(): Disposable {
        val disposable = ProductRepository
            .getProductsAccessories()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {setAccessoriesList(it.data); mAccessoriesList.value = it.data},
                onError = { Log.d(Constants.TAG, it.toString())},
                onComplete = { Log.d(Constants.TAG, "Loading ProductAccessories ok")}
            )
        return disposable
    }

    private fun setAccessoriesList(productsMen: ArrayList<Product>){
        productsMen.toObservable()
            .subscribeBy (
                onNext = {
                    Log.d (Constants.TAG, it.title)
                    Log.d (Constants.TAG, it.note)
                    Log.d (Constants.TAG, it.main_image)
                },
                onError = { Log.d(Constants.TAG, it.toString()); isLoading.value = false },
                onComplete = { Log.d(Constants.TAG, "Loading ProductAccessories ok"); isLoading.value = false}
            )
    }
}