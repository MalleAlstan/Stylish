package com.john.stylish.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.john.stylish.R
import com.john.stylish.Stylish
import com.john.stylish.model.objects.Product.Product
import com.john.stylish.utils.Constants.APP_NAME


class MainViewModel: ViewModel(){

    var fragType = MutableLiveData<FRAG_TYPE>()
    var catalogType = MutableLiveData<CATALOG_TYPE>()
    var detailProduct = MutableLiveData<Product>()
    var lastFragType = FRAG_TYPE.HOME

    init {
        fragType.value = FRAG_TYPE.HOME
        catalogType.value = CATALOG_TYPE.LINEAR
    }

    enum class FRAG_TYPE{
        HOME,
        CATALOG,
        CART,
        PROFILE,
        DETAIL;

        fun getTitle(): String {
            var title = APP_NAME
            when(this){
                HOME -> title = APP_NAME
                CATALOG -> title = Stylish.getAppContext().getString(R.string.catalog)
                CART -> title = Stylish.getAppContext().getString(R.string.cart)
                PROFILE -> title = Stylish.getAppContext().getString(R.string.profile)
            }
            return title
        }

        fun getHomeVisibility(): Boolean{
            return this == HOME
        }

        fun getCatalogVisibility(): Boolean{
            return this == CATALOG
        }

        fun getCartVisibility(): Boolean{
            return this == CART
        }

        fun getProfileVisibility(): Boolean{
            return this == PROFILE
        }

        fun getDetailVisibility(): Boolean{
            return this == DETAIL
        }
    }

    enum class CATALOG_TYPE {
        LINEAR,
        GRID;

        fun getLinearVisibility(): Boolean{
            return this != LINEAR

        }
    }
}