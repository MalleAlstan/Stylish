package com.john.stylish.utils

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.john.stylish.Stylish

object Utils {

    var mContext = Stylish.getAppContext()

    fun showToast(message: String) =  {
        Toast.makeText(mContext, message, LENGTH_SHORT)
    }

}