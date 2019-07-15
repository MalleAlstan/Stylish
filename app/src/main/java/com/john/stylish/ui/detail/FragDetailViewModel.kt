package com.john.stylish.ui.detail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.john.stylish.model.objects.Product.Product

class FragDetailViewModel : ViewModel() {

    var isLoading = MutableLiveData<Boolean>()
    var detailProduct = MutableLiveData<Product>()

    init {
        isLoading.value = false
    }
}