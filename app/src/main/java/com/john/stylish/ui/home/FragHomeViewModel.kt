package com.john.stylish.ui.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.john.stylish.model.responses.HotsResponse
import com.john.stylish.utils.Constants.Companion.APP_NAME

class FragHomeViewModel : ViewModel() {

    var mTitle = MutableLiveData<String>()

    init {
        mTitle.value = "Loading..."
    }

    fun setTitle(hotsResponse: HotsResponse){
        var hotsList = " "

        for (hots in hotsResponse.data){
            hotsList = hotsList + "< " + hots.title + " >" + "\n\n"
            var i = 0
            for (product in hots.products){
                i = i + 1
                hotsList = hotsList + i + ". "+ product.title +  " - " + product.price + "$ \n"
                hotsList = hotsList + product.description + "\n"
                hotsList = hotsList + "" + product.wash + "\n"
                for (color in product.colors){
                    hotsList = hotsList + color.name + "(" + color.code + ") "
                }
                hotsList = hotsList + "\n" + "Varients: \n"
                for (varients in product.variants){
                    hotsList = hotsList + varients.color_code + "(" + varients.size + ")x" + varients.stock + " "
                }
                hotsList = hotsList + "\n\n"
            }
            hotsList = hotsList + "\n"
        }
        mTitle.value = hotsList
    }
}