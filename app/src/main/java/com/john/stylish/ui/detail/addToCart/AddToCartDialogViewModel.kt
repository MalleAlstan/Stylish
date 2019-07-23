package com.john.stylish.ui.detail.addToCart

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.john.stylish.model.objects.Product.Product

class AddToCartDialogViewModel : ViewModel() {

    var isLoading = MutableLiveData<Boolean>()
    var selectedProduct = MutableLiveData<Product>()

    var isColorSelected = MutableLiveData<Boolean>()
    var isSizeSelected = MutableLiveData<Boolean>()

    var isStockEnough = MutableLiveData<Boolean>()
    var isAddable = MutableLiveData<Boolean>()
    var isRemovable = MutableLiveData<Boolean>()
    var isAmountAvailable = MutableLiveData<Boolean>()

    var amount = MutableLiveData<Int>()

    var counter = 0

    init {
        isLoading.value = false
        isColorSelected.value = true
        isSizeSelected.value = true
        amount.value = 0
    }

    fun setAmount(setAmount: Int) {
        counter = setAmount
        amount.value = counter

        isStockEnough.value = counter <= selectedProduct.value!!.variants[0].stock
        isAddable.value = counter < selectedProduct.value!!.variants[0].stock
        isRemovable.value = counter > 0
        isAmountAvailable.value = counter > 0 && counter <= selectedProduct.value!!.variants[0].stock
    }

    fun addItem() {
        if (counter < selectedProduct.value!!.variants[0].stock) {
            counter += 1
            amount.value = counter
        }
    }

    fun removeItem() {
        if (amount.value!! > 0) {
            counter -= 1
            amount.value = counter
        }
    }

    fun isNeedShowStock(): Boolean {
        return isColorSelected.value!! && isSizeSelected.value!!
    }
}