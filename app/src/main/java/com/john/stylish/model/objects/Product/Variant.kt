package com.john.stylish.model.objects.Product

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Variant(

    @SerializedName("color_code")
    val color_code: String = "",
    @SerializedName("size")
    val size: String = "",
    @SerializedName("stock")
    val stock: Int = 0
): Serializable