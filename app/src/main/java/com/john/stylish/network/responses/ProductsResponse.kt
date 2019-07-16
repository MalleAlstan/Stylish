package com.john.stylish.network.responses

import com.google.gson.annotations.SerializedName
import com.john.stylish.model.objects.Hots
import com.john.stylish.model.objects.Product.Product

data class ProductsResponse(
    @SerializedName("data")
    val data: ArrayList<Product>,

    @SerializedName("paging")
    val paging: Int
)