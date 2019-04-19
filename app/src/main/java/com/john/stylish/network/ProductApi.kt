package com.john.stylish.network

import com.john.stylish.model.Campaign
import com.john.stylish.model.Hots
import com.john.stylish.model.Product.Product
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductApi {

    @GET("/marketing/campaigns")
    fun getMarketingCampaigns(): Observable<List<Campaign>>

    @GET("/marketing/hots")
    fun getMarketingHots(): Observable<List<Hots>>

    @GET("/products/all")
    fun getProductsAll(): Observable<List<Product>>

    @GET("/products/women")
    fun getProductsWomen(): Observable<List<Product>>

    @GET("/products/men")
    fun getProductsMen(): Observable<List<Product>>

    @GET("/products/accessories")
    fun getProductsAccessories(): Observable<List<Product>>

    @GET("/products/search")
    fun getProductsSearch(): Observable<List<Product>>

    @GET("/products/details")
    fun getProductsDetails(): Observable<Product>
}