package com.john.stylish.ui.profile

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.john.stylish.R
import com.john.stylish.databinding.FragProfileBinding
import kotlinx.android.synthetic.main.frag_profile.*

class FragProfile: Fragment(), View.OnClickListener{

    lateinit var mFragProfileViewModel: FragProfileViewModel
    lateinit var mFragProfileBinding: FragProfileBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return setDataBinding(inflater, container)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setLiveDataObservers()
        setOnClickListeners()
    }


    private fun setLiveDataObservers() {
    }

    private fun setDataBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        mFragProfileViewModel = ViewModelProviders.of(this).get(FragProfileViewModel::class.java)
        mFragProfileBinding = DataBindingUtil.inflate(inflater, R.layout.frag_profile, container , false)
        mFragProfileBinding.fragProfileViewModel = mFragProfileViewModel
        mFragProfileBinding.setLifecycleOwner(activity)

        return mFragProfileBinding.root
    }

    private fun setOnClickListeners(){
        linear_profile_awaiting_payment.setOnClickListener(this)
        linear_profile_awaiting_shipment.setOnClickListener(this)
        linear_profile_shipped.setOnClickListener(this)
        linear_profile_awaiting_review.setOnClickListener(this)
        linear_profile_exchange.setOnClickListener(this)
        linear_profile_starred.setOnClickListener(this)
        linear_profile_notification.setOnClickListener(this)
        linear_profile_refunded.setOnClickListener(this)
        linear_profile_address.setOnClickListener(this)
        linear_profile_customer_service.setOnClickListener(this)
        linear_profile_system_feedback.setOnClickListener(this)
        linear_profile_register_cellphone.setOnClickListener(this)
        linear_profile_settings.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        Toast.makeText(activity, getString(R.string.on_working), Toast.LENGTH_SHORT).show()
    }
}