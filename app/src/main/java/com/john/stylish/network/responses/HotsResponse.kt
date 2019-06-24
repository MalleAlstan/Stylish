package com.john.stylish.network.responses

import com.google.gson.annotations.SerializedName
import com.john.stylish.model.objects.Hots

data class HotsResponse(
    @SerializedName("data")
    val data: ArrayList<Hots>
)