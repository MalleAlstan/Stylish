package com.john.stylish.model.objects

import com.google.gson.annotations.SerializedName
import com.john.stylish.model.objects.Product.Product
import java.util.*
import kotlin.collections.ArrayList


class Hots(
    @SerializedName("title")
    val title: String = "",
    @SerializedName("products")
    val products: ArrayList<Product> = ArrayList()
) {

    fun toObjList(): ArrayList<Any> {
        val list = ArrayList<Any>()
        list.add(title)
        list.addAll(products)
        return list
    }
}
