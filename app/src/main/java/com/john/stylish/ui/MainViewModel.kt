package com.john.stylish.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.john.stylish.utils.Constants.Companion.APP_NAME
import com.john.stylish.utils.Constants.Companion.CART_TITLE
import com.john.stylish.utils.Constants.Companion.CATEGORY_TITLE
import com.john.stylish.utils.Constants.Companion.PROFILE_TITLE
import com.john.stylish.ui.MainViewModel.FRAG_TYPE as FRAG_TYPE1


class MainViewModel: ViewModel(){

    var fragType = MutableLiveData<FRAG_TYPE>()

    enum class FRAG_TYPE{
        HOME,
        CATEGORY,
        CART,
        PROFILE;

        fun getTitle(): String {
            var title = APP_NAME
            when(this){
                HOME -> title = APP_NAME
                CATEGORY -> title = CATEGORY_TITLE
                CART -> title = CART_TITLE
                PROFILE -> title = PROFILE_TITLE
            }
            return title
        }
    }

    init {
        fragType.value = FRAG_TYPE.HOME
    }
}