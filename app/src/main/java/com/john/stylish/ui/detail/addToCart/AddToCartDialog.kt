package com.john.stylish.ui.detail.addToCart

import android.arch.lifecycle.Observer
import android.support.design.widget.BottomSheetDialogFragment
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.TextWatcher
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.john.stylish.databinding.DialogAddToCartBinding
import com.john.stylish.model.objects.Product.Product
import com.john.stylish.utils.Constants.KEY_SELECTED_PRODUCT
import kotlinx.android.synthetic.main.dialog_add_to_cart.*
import android.text.Editable
import android.util.Log
import com.john.stylish.R
import com.john.stylish.ui.detail.AddToCartColorsAdapter


class AddToCartDialog : BottomSheetDialogFragment(), View.OnClickListener {

    private lateinit var mAddToCartDialogViewModel: AddToCartDialogViewModel
    private lateinit var mAddToCartDialogBinding: DialogAddToCartBinding
    private lateinit var mSelectedColorObserver: Observer<String>
    private lateinit var mSelectedSizeObserver: Observer<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return setDataBinding(inflater, container)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setLiveDataObservers()
        showAddToCartDialogView()
        setOnClickListeners()
    }

    private fun setDataBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        mAddToCartDialogViewModel = ViewModelProviders.of(this).get(AddToCartDialogViewModel::class.java)
        mAddToCartDialogViewModel.selectedProduct.value = arguments?.getSerializable(KEY_SELECTED_PRODUCT) as Product;
        mAddToCartDialogBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_add_to_cart, container, false)
        mAddToCartDialogBinding.addToCartDialogViewModel = mAddToCartDialogViewModel
        mAddToCartDialogBinding.setLifecycleOwner(this)
        return mAddToCartDialogBinding.root
    }

    private fun setLiveDataObservers() {
        mSelectedColorObserver = Observer {
            updateStockNumber()
        }
        mSelectedSizeObserver = Observer {
            updateStockNumber()
        }

        mAddToCartDialogViewModel.selectedColorCode.observe(this, mSelectedColorObserver)
        mAddToCartDialogViewModel.selectedSize.observe(this, mSelectedSizeObserver)
    }

    private fun showAddToCartDialogView(){
        var product = mAddToCartDialogViewModel.selectedProduct.value

        recyclerView_add_to_cart_select_color.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerView_add_to_cart_select_color.adapter = AddToCartColorsAdapter(product!!.colors, mAddToCartDialogViewModel)

        recyclerView_add_to_cart_select_size.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerView_add_to_cart_select_size.adapter = AddToCartSizesAdapter(this, product!!.sizes, mAddToCartDialogViewModel)
    }

    private fun setOnClickListeners(){
        button_add_to_cart_close.setOnClickListener(this)
        button_add_to_cart_add.setOnClickListener(this)
        button_add_to_cart_remove.setOnClickListener(this)
        button_add_to_cart_add_to_cart.setOnClickListener(this)
        edit_add_to_cart_amount_editor.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (!edit_add_to_cart_amount_editor.text.toString().isEmpty())
                    mAddToCartDialogViewModel.setAmount(edit_add_to_cart_amount_editor.text.toString().toInt())
            }
        });
    }

    override fun onClick(v: View?) {
        when(v){
            button_add_to_cart_close -> dismiss()
            button_add_to_cart_add -> mAddToCartDialogViewModel.addItem()
            button_add_to_cart_remove -> mAddToCartDialogViewModel.removeItem()
            button_add_to_cart_add_to_cart -> addToCart()
        }
    }

    private fun updateStockNumber(){
        if (mAddToCartDialogViewModel.isSizeSelected.value == true) {

            for (variant in  mAddToCartDialogViewModel.selectedProduct.value!!.variants) {
                if (mAddToCartDialogViewModel.selectedColorCode.value.equals(variant.color_code)) {

                    for (size in variant.size) {
                        if (mAddToCartDialogViewModel.selectedSize.value.equals(variant.size)) {

                            mAddToCartDialogViewModel.stock.value = variant.stock
                            if (!edit_add_to_cart_amount_editor.text.toString().isEmpty())
                                mAddToCartDialogViewModel.setAmount(edit_add_to_cart_amount_editor.text.toString().toInt())
                        }
                    }
                }
            }
        }
    }

    private fun addToCart (){
    }

    companion object {
        fun newInstance(): AddToCartDialog {
            return AddToCartDialog()
        }
    }
}