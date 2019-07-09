package com.john.stylish.ui.catalog

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.john.stylish.utils.Constants

class FragCatalogViewModel : ViewModel() {

    var catalogType = MutableLiveData<CATALOG_TYPE>()

    init {
        catalogType.value = CATALOG_TYPE.MEN
    }

    enum class CATALOG_TYPE{
        MEN,
        WOMEN,
        OTHERS;
    }
}