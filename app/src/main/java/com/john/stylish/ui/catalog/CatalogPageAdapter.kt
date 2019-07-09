package com.john.stylish.ui.catalog

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.john.stylish.R
import com.john.stylish.ui.catalog.Catelogs.FragMen
import com.john.stylish.ui.catalog.Catelogs.FragOthers
import com.john.stylish.ui.catalog.Catelogs.FragWomen


class CatalogPageAdapter(context: Context, fm: FragmentManager): FragmentPagerAdapter(fm) {

    private val mContext: Context

    init {
        mContext = context.getApplicationContext()
    }

    override fun getItem(position: Int): Fragment? {
        when (TABS[position]) {
            MEN -> return FragMen()
            WOMEN -> return FragWomen()
            OTHERS -> return FragOthers()
        }
        return null
    }

    override fun getCount(): Int {
        return TABS.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (TABS[position]) {
            MEN -> return mContext.getResources().getString(R.string.men)
            WOMEN -> return mContext.getResources().getString(R.string.women)
            OTHERS -> return mContext.getResources().getString(R.string.others)
        }
        return null
    }

    companion object {
        private val MEN = 0
        private val WOMEN = 1
        private val OTHERS = 2

        private val TABS = intArrayOf(MEN, WOMEN, OTHERS)
    }
}