package com.john.stylish.ui.detail.addToCart

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.john.stylish.model.objects.Product.Color
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

    var selectedColorCode =  MutableLiveData<String>()
    var selectedSize = MutableLiveData<String>()
    var stock = MutableLiveData<Int>()
    var amount = MutableLiveData<Int>()

    var counter = 0

    init {
        isLoading.value = false
        isColorSelected.value = false
        isSizeSelected.value = false
        isAddable.value = false
        isRemovable.value = false
        isAmountAvailable.value = false
        selectedColorCode.value = ""
        selectedSize.value = ""
        stock.value = 0
        amount.value = 0
    }

    fun setAmount(setAmount: Int) {
        counter = setAmount
        amount.value = counter

        isStockEnough.value = counter <= stock.value!!
        isAddable.value = counter < stock.value!!
        isRemovable.value = counter > 0
        isAmountAvailable.value = counter > 0 && counter <= stock.value!!
    }

    fun addItem() {
        if (counter < stock.value!!) {
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
}