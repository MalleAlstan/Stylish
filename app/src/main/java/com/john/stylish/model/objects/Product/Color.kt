package com.john.stylish.model.objects.Product

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Color(

    @SerializedName("name")
    val name: String,
    @SerializedName("code")
    val code: String
): Serializable