package com.john.stylish.ui.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.database.Observable
import android.util.Log
import com.john.stylish.model.objects.Hots
import com.john.stylish.model.responses.HotsResponse
import com.john.stylish.network.ApiServiceBuilder
import com.john.stylish.utils.Constants
import com.john.stylish.utils.Constants.TAG
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.internal.util.HalfSerializer.onNext
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import java.util.*

class FragHomeViewModel : ViewModel() {

    var isLoading = MutableLiveData<Boolean>()
    var mHotsList= MutableLiveData<ArrayList<Any>>()

    init {
        isLoading.value = false
    }

    fun getHotsList(): Disposable{

        isLoading.value = true
        val getHotsListCall = ApiServiceBuilder.build().getMarketingHots()

        val disposable = getHotsListCall
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { setHotsList(it); isLoading.value = false },
                onError = { Log.d(TAG, it.toString()); isLoading.value = false },
                onComplete = { Log.d(TAG, "Loading ok")}
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
                onError = {Log.d(TAG,"error")}
            )

        return combineList
    }
}