package com.john.stylish.ui.catalog

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.john.stylish.R
import com.john.stylish.ui.catalog.men.FragMen
import com.john.stylish.ui.catalog.accessories.FragAccessories
import com.john.stylish.ui.catalog.women.FragWomen


class CatalogPageAdapter(context: Context, fm: FragmentManager): FragmentPagerAdapter(fm) {

    private val mContext: Context

    init {
        mContext = context
    }

    override fun getItem(position: Int): Fragment? {
        when (TABS[position]) {
            WOMEN -> return FragWomen()
            MEN -> return FragMen()
            ACCESSORIES -> return FragAccessories()
        }
        return null
    }

    override fun getCount(): Int {
        return TABS.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (TABS[position]) {
            WOMEN -> return mContext.getResources().getString(R.string.women)
            MEN -> return mContext.getResources().getString(R.string.men)
            ACCESSORIES -> return mContext.getResources().getString(R.string.accessories)
        }
        return null
    }

    companion object {

        private val WOMEN = 0
        private val MEN = 1
        private val ACCESSORIES = 2

        private val TABS = intArrayOf(WOMEN, MEN, ACCESSORIES)
    }
}