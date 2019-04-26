package com.john.stylish.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.john.stylish.utils.Constants.APP_NAME
import com.john.stylish.utils.Constants.CART_TITLE
import com.john.stylish.utils.Constants.CATALOG_TITLE
import com.john.stylish.utils.Constants.PROFILE_TITLE
import com.john.stylish.ui.MainViewModel.FRAG_TYPE as FRAG_TYPE1


class MainViewModel: ViewModel(){

    var fragType = MutableLiveData<FRAG_TYPE>()

    init {
        fragType.value = FRAG_TYPE.HOME
    }

    enum class FRAG_TYPE{
        HOME,
        CATALOG,
        CART,
        PROFILE;

        fun getTitle(): String {
            var title = APP_NAME
            when(this){
                HOME -> title = APP_NAME
                CATALOG -> title = CATALOG_TITLE
                CART -> title = CART_TITLE
                PROFILE -> title = PROFILE_TITLE
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
    }
}