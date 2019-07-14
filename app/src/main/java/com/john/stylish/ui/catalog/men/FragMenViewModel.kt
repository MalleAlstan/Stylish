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
    var mMenList= MutableLiveData<ArrayList<Product>>() //MutableLiveData<ArrayList<Any>>()

    init {
        isLoading.value = false
    }

    fun getProductsMen(): Disposable {
        val disposable = ProductRepository
            .getProductsMen()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {setMenList(it.data); mMenList.value = it.data},
                onError = { Log.d(Constants.TAG, it.toString())},
                onComplete = { Log.d(Constants.TAG, "Loading ProductsMen ok")}
            )
        return disposable
    }

    private fun setMenList(productsMen: ArrayList<Product>){
        productsMen.toObservable()
            .subscribeBy (
                onNext = {
                    Log.d (TAG, it.title)
                    Log.d (TAG, it.note)
                    Log.d (TAG, it.main_image)
                },
                onError = { Log.d(Constants.TAG, it.toString()); isLoading.value = false },
                onComplete = { Log.d(Constants.TAG, "Loading ProductsMen ok"); isLoading.value = false}
            )
    }
}