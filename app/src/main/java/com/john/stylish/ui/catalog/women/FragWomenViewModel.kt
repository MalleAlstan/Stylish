package com.john.stylish.ui.catalog.women

import android.util.Log
import com.john.stylish.R
import com.john.stylish.Stylish
import com.john.stylish.base.BaseCatalogViewModel
import com.john.stylish.model.Repository.ProductRepository
import com.john.stylish.model.objects.Product.Product
import com.john.stylish.utils.Constants
import com.john.stylish.utils.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import java.util.*

class FragWomenViewModel: BaseCatalogViewModel() {

    override fun getProductList(){
        ProductRepository
            .getProductsWomen(nextPage.toString())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    productList.value = (productList.value!! + it.data) as ArrayList<Product>
                    if (it.paging == 0) hasNexPage = false
                    if (hasNexPage) nextPage = it.paging;
                },
                onError = {
                    Log.d(Constants.TAG, it.toString())
                    isLoading.value = false
                    Utils.showToast(Stylish.getAppContext().getString(R.string.cant_get_product_list))
                },
                onComplete = {
                    isLoading.value = false
                    Log.d(Constants.TAG, "Loading ProductWomen ok")
                }
            )
    }
}