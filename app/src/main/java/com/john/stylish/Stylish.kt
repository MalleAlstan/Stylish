package com.john.stylish

import android.app.Application
import android.content.Context

class Stylish : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: Stylish? = null

        fun getAppContext() : Context {
            return instance!!.applicationContext
        }
    }
}