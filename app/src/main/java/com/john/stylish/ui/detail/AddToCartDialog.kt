package com.john.stylish.ui.detail

import android.support.design.widget.BottomSheetDialogFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import com.john.stylish.R
import com.john.stylish.databinding.DialogAddToCartBinding
import com.john.stylish.ui.MainViewModel
import com.john.stylish.ui.home.HotsListAdapter
import io.reactivex.disposables.Disposable


class AddToCartDialog : BottomSheetDialogFragment() {

    lateinit var mMainViewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_add_to_cart, container,false);
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onPause() {
        super.onPause()
    }

    companion object {

        fun newInstance(): AddToCartDialog {
            return AddToCartDialog()
        }
    }
}