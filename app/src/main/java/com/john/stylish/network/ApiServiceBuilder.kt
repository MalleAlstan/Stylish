package com.john.stylish.network

import android.util.Log
import com.john.stylish.utils.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
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