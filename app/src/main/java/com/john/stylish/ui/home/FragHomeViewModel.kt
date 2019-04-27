package com.john.stylish.ui.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.john.stylish.model.objects.Hots
import com.john.stylish.model.responses.HotsResponse
import com.john.stylish.network.ApiServiceBuilder
import com.john.stylish.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

class FragHomeViewModel : ViewModel() {

    var isLoading = MutableLiveData<Boolean>()
    var mHotsList= MutableLiveData<ArrayList<Any>>()

    init {
        isLoading.value = false
    }

    fun getHotsList(): Disposable{
        isLoading.value = true
        val getHotsListCall = ApiServiceBuilder.build().getMarketingHots()
        val disposable = getHotsListCall.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { hotsResponse -> setHotsList(hotsResponse)
                    isLoading.value = false
                },
                { error -> Log.d(Constants.TAG, error.toString())
                    isLoading.value = false
                }
            )
        return disposable
    }

    private fun setHotsList(hotsResponse: HotsResponse){
        mHotsList.value = combineHotsList(hotsResponse.data)
    }

    private fun combineHotsList(hotsList: ArrayList<Hots>): ArrayList<Any> {
        val combineList = ArrayList<Any>()
        hotsList.forEach {
            combineList.addAll(it.toObjList())
        }
        return combineList
    }
}