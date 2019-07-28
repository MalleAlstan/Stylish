package com.john.stylish.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.john.stylish.model.Repository.ProductRepository
import com.john.stylish.model.objects.Product.Product
import com.john.stylish.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import java.util.ArrayList

abstract class BaseCatalogViewModel : ViewModel() {

    var isLoading = MutableLiveData<Boolean>()
    var productList= MutableLiveData<ArrayList<Product>>()
    var nextPage = 0
    var hasNexPage = true

    init {
        isLoading.value = false
        productList.value = ArrayList()
    }

    fun reset(){
        productList.value = ArrayList()
        nextPage = 0
        hasNexPage = true
    }

    open fun getProductList(){}
}