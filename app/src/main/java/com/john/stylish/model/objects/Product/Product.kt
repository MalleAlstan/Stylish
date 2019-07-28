package com.john.stylish.model.objects.Product

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Product(

    @SerializedName("id")
    val id: Long = 0,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("price")
    val price: Int = 0,
    @SerializedName("texture")
    val texture: String = "",
    @SerializedName("wash")
    val wash: String = "",
    @SerializedName("place")
    val place: String = "",
    @SerializedName("note")
    val note: String = "",
    @SerializedName("story")
    val story: String = "",
    @SerializedName("colors")
    val colors: ArrayList<Color> = ArrayList(),
    @SerializedName("sizes")
    val sizes: ArrayList<String> = ArrayList(),
    @SerializedName("variants")
    val variants: ArrayList<Variant> = ArrayList(),
    @SerializedName("main_image")
    val main_image: String = "",
    @SerializedName("images")
    val images: ArrayList<String> = ArrayList(),

    val count: Int = 0
) : Serializable