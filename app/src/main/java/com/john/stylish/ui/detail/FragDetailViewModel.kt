package com.john.stylish.ui.detail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.john.stylish.model.Repository.ProductRepository
import com.john.stylish.model.objects.Hots
import com.john.stylish.network.responses.HotsResponse
import com.john.stylish.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import java.util.ArrayList

class FragDetailViewModel : ViewModel() {

    var isLoading = MutableLiveData<Boolean>()
    var mHotsList= MutableLiveData<ArrayList<Any>>()

    init {
        isLoading.value = false
    }

    fun getHotsList(): Disposable {
        val disposable = ProductRepository
            .getHotsProductList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { setHotsList(it) },
                onError = { Log.d(Constants.TAG, it.toString())},
                onComplete = { Log.d(Constants.TAG, "Loading HotList ok")}
            )
        return disposable
    }

    private fun setHotsList(hotsResponse: HotsResponse){
        mHotsList.value = combineHotsList(hotsResponse.data)
    }

    private fun combineHotsList(hotsList: ArrayList<Hots>): ArrayList<Any> {
        val combineList = ArrayList<Any>()

        hotsList.toObservable()
            .map { it.toObjList() }
            .map { combineList.addAll(it) }
            .subscribeBy(
                onError = { Log.d(Constants.TAG, it.toString())}
            )

        return combineList
    }
}