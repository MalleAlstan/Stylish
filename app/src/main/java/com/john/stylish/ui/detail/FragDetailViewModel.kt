package com.john.stylish.ui.detail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.john.stylish.model.objects.Product.Product
import com.john.stylish.model.objects.Product.Variant
import kotlinx.android.synthetic.main.frag_detail.*

class FragDetailViewModel : ViewModel() {

    var isLoading = MutableLiveData<Boolean>()
    var selectedProduct = MutableLiveData<Product>()

    init {
        isLoading.value = false
    }

    fun getSizeString(): String{
        if (selectedProduct.value!!.sizes.size > 1) {
            return selectedProduct.value!!.sizes[0] + " - " +
                    selectedProduct.value!!.sizes[selectedProduct.value!!.sizes.size - 1]
        } else return selectedProduct.value!!.sizes[0]
    }

    fun getVariantString(): String{
        var totalStock = 0
        for (model in selectedProduct.value!!.variants) totalStock += model.stock

        return totalStock.toString()
    }
}