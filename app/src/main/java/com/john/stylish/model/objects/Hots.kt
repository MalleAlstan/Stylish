package com.john.stylish.model.objects

import com.google.gson.annotations.SerializedName
import com.john.stylish.model.objects.Product.Product

data class Hots(

    @SerializedName("title")
    val title: String,
    @SerializedName("products")
    val products: Array<Product>
)