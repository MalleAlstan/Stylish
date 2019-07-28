package com.john.stylish.ui.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.john.stylish.model.Repository.ProductRepository
import com.john.stylish.model.objects.Hots
import com.john.stylish.network.responses.HotsResponse
import com.john.stylish.utils.Constants.TAG
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import java.util.*

class FragHomeViewModel : ViewModel() {

    var isLoading = MutableLiveData<Boolean>()
    var mHotsList= MutableLiveData<ArrayList<Any>>()

    init {
        isLoading.value = false
    }

    fun getHotsList() {
        ProductRepository
            .getHotsProductList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { setHotsList(it) },
                onError = { Log.d(TAG, it.toString())},
                onComplete = { Log.d(TAG, "Loading HotList ok")}
            )
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
                onError = {Log.d(TAG, it.toString())}
            )

        return combineList
    }
}