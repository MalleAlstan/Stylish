package com.john.stylish.model.objects

import com.google.gson.annotations.SerializedName

data class Campaign(

    @SerializedName("id")
    val id: Long = 0,
    @SerializedName("picture")
    val picture: String = "",
    @SerializedName("story")
    val story: String = ""
)