package com.john.stylish.network

import com.john.stylish.utils.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiServiceBuilder {

    fun build(): StylishApi {

        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()) // 使用 Gson 解析
            .baseUrl(Constants.STYLISH_BASE_URL + "/")
            .build()

        val apiService = retrofit.create(StylishApi::class.java)

        return apiService
    }
}