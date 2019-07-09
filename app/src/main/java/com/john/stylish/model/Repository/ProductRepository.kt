package com.john.stylish.model.Repository

import android.util.Log
import com.john.stylish.network.responses.HotsResponse
import com.john.stylish.network.ApiServiceBuilder
import com.john.stylish.utils.Constants
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


object ProductRepository {

    fun getHotsProductList(): Observable<HotsResponse> {

        val getHotsListCall = ApiServiceBuilder.build().getMarketingHots()
        Log.d(Constants.TAG, "Load HotList from API")

        return getHotsListCall.subscribeOn(Schedulers.io())
    }
}