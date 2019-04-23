package com.john.stylish.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.provider.SyncStateContract
import com.john.stylish.utils.Constants
import com.john.stylish.utils.Constants.Companion.APP_NAME
import com.john.stylish.utils.Constants.Companion.FRAG_HOME


class MainViewModel: ViewModel(){

    var currentTitle = MutableLiveData<String>()

    init {
        currentTitle.value = APP_NAME
    }
}