package com.john.stylish.model.responses

import com.google.gson.annotations.SerializedName
import com.john.stylish.model.objects.Hots
import com.john.stylish.model.objects.Product.Product

data class HotsResponse(
    @SerializedName("data")
    val data: Array<Hots>
)