package com.john.stylish.model.objects.Product

import com.google.gson.annotations.SerializedName

data class Product(

    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("texture")
    val texture: String,
    @SerializedName("wash")
    val wash: String,
    @SerializedName("place")
    val place: String,
    @SerializedName("note")
    val note: String,
    @SerializedName("story")
    val story: String,
    @SerializedName("colors")
    val colors: Array<Color>,
    @SerializedName("sizes")
    val sizes: Array<String>,
    @SerializedName("variants")
    val variants: Array<Variant>,
    @SerializedName("main_images")
    val main_image:Array<String>,
    @SerializedName("images")
    val images: Array<String>
)