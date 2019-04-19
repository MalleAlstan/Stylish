package com.john.stylish.model.Product

data class Product(

    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val texture: String,
    val texturewash: String,
    val place: String,
    val note: String,
    val story: String,
    val colors: Array<Color>,
    val sizes: Array<String>,
    val variants: Array<Variant>,
    val main_image:Array<String>,
    val images: Array<String>
)