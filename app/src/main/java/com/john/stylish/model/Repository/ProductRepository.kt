package com.john.stylish.model.Repository

import android.util.Log
import com.john.stylish.model.objects.Product.Product
import com.john.stylish.network.responses.HotsResponse
import com.john.stylish.network.ApiServiceBuilder
import com.john.stylish.network.responses.ProductsResponse
import com.john.stylish.utils.Constants
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


object ProductRepository {

    fun getHotsProductList(): Observable<HotsResponse> {

        val getHotsListCall = ApiServiceBuilder.build().getMarketingHots()
        Log.d(Constants.TAG, "Load HotList from API")

        return getHotsListCall.subscribeOn(Schedulers.io())
    }

    fun getProductsMen(page: String): Observable<ProductsResponse> {

        val getProductsMenCall = ApiServiceBuilder.build().getProductsMen(page)
        Log.d(Constants.TAG, "Load ProductsMenList from API")

        return getProductsMenCall.subscribeOn(Schedulers.io())
    }

    fun getProductsWomen(page: String): Observable<ProductsResponse> {

        val getProductsWomenCall = ApiServiceBuilder.build().getProductsWomen(page)
        Log.d(Constants.TAG, "Load ProductsWomenList from API")

        return getProductsWomenCall.subscribeOn(Schedulers.io())
    }

    fun getProductsAccessories(page: String): Observable<ProductsResponse> {

        val getProductsWomenCall = ApiServiceBuilder.build().getProductsAccessories(page)
        Log.d(Constants.TAG, "Load ProductssAccessoriesList from API")

        return getProductsWomenCall.subscribeOn(Schedulers.io())
    }
}