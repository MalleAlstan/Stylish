package com.john.stylish.ui.catalog.accessories

import android.util.Log
import android.widget.Toast
import com.john.stylish.base.BaseCatalogViewModel
import com.john.stylish.model.Repository.ProductRepository
import com.john.stylish.model.objects.Product.Product
import com.john.stylish.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import java.util.*

class FragAccessoriesViewModel : BaseCatalogViewModel() {

    override fun getProductList(){
        ProductRepository
            .getProductsAccessories(nextPage.toString())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    productList.value = (productList.value!! + it.data) as ArrayList<Product>
                    if (it.paging == 0) hasNexPage = false
                    if (hasNexPage) nextPage = it.paging;
                },
                onError = {
                    isLoading.value = false
                    Log.d(Constants.TAG, it.toString())

                },
                onComplete = {
                    isLoading.value = false
                    Log.d(Constants.TAG, "Loading ProductAccessories ok")
                }
            )
    }
}