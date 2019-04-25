package com.john.stylish.model.objects.Product

import com.google.gson.annotations.SerializedName

data class Variant(

    @SerializedName("color_code")
    val color_code: String,
    @SerializedName("size")
    val size: String,
    @SerializedName("stock")
    val stock: Int
)