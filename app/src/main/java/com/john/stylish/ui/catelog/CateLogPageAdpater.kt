package com.john.stylish.ui.catelog

import android.R
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


class CateLogPageAdapter(context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val mContext: Context

    init {
        mContext = context.getApplicationContext()
    }

    override fun getItem(position: Int): Fragment? {
        when (TABS[position]) {
//            CONTACTS -> return ContactsFragment.newInstance()
//            CALLS -> return CallsFragment.newInstance()
//            CHATS -> return ChatsFragment.newInstance()
        }
        return null
    }

    override fun getCount(): Int {
        return TABS.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (TABS[position]) {
//            CONTACTS -> return mContext.getResources().getString(R.string.contacts)
//            CALLS -> return mContext.getResources().getString(R.string.calls)
//            CHATS -> return mContext.getResources().getString(R.string.chats)
        }
        return null
    }

    companion object {
        private val CONTACTS = 0
        private val CALLS = 1
        private val CHATS = 2

        private val TABS = intArrayOf(CONTACTS, CHATS)
    }
}