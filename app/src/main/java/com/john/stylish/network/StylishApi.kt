package com.john.stylish.network

import com.john.stylish.model.objects.Campaign
import com.john.stylish.model.objects.Hots
import com.john.stylish.model.objects.Product.Product
import com.john.stylish.model.responses.HotsResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface StylishApi {

    @GET("marketing/campaigns")
    fun getMarketingCampaigns(): Observable<List<Campaign>>

    @GET("marketing/hots")
    fun getMarketingHots(): Observable<HotsResponse>

    @GET("products/all")
    fun getProductsAll(): Observable<List<Product>>

    @GET("products/women")
    fun getProductsWomen(): Observable<List<Product>>

    @GET("products/men")
    fun getProductsMen(): Observable<List<Product>>

    @GET("products/accessories")
    fun getProductsAccessories(): Observable<List<Product>>

    @GET("products/search")
    fun getProductsSearch(): Observable<List<Product>>

    @GET("products/details")
    fun getProductsDetails(): Observable<Product>
}