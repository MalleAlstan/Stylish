package com.john.stylish.model.objects.Product

import com.google.gson.annotations.SerializedName

data class Color(

    @SerializedName("name")
    val name: String,
    @SerializedName("code")
    val code: String
)