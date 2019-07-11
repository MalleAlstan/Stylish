package com.john.stylish.network

import com.john.stylish.model.objects.Campaign
import com.john.stylish.model.objects.Product.Product
import com.john.stylish.network.responses.HotsResponse
import com.john.stylish.network.responses.ProductsResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface StylishApi {

    @GET("marketing/campaigns")
    fun getMarketingCampaigns(): Observable<ArrayList<Campaign>>

    @GET("marketing/hots")
    fun getMarketingHots(): Observable<HotsResponse>

    @GET("products/all")
    fun getProductsAll(): Observable<ArrayList<Product>>

    @GET("products/women")
    fun getProductsWomen(): Observable<ProductsResponse>

    @GET("products/men")
    fun getProductsMen(): Observable<ProductsResponse>

    @GET("products/accessories")
    fun getProductsAccessories(): Observable<ProductsResponse>

    @GET("products/search")
    fun getProductsSearch(): Observable<ArrayList<Product>>

    @GET("products/details")
    fun getProductsDetails(): Observable<Product>
}