package com.john.stylish.model.objects

import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("id")
    val id: Long,
    @SerializedName("id")
    val provider: String,
    @SerializedName("id")
    val name: String,
    @SerializedName("id")
    val email: String,
    @SerializedName("id")
    val picture: String
)