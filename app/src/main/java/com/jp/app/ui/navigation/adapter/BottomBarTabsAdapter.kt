package com.jp.app.ui.navigation.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import android.view.ViewGroup
import com.jp.app.ui.navigation.view.*

class BottomBarTabsAdapter
constructor(fm: FragmentManager, numTabs: Int) : FragmentStatePagerAdapter(fm) {

    var mNumOfTabs: Int = numTabs

    private var mNavFragment1: NavFragment1? = null
    private var mNavFragment2: NavFragment2? = null
    private var mNavFragment3: NavFragment3? = null
    private var mNavFragment4: NavFragment4? = null
    private var mNavFragment5: NavFragment5? = null

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as Fragment
        when (position) {
            0 -> mNavFragment1 = fragment as NavFragment1
            1 -> mNavFragment2 = fragment as NavFragment2
            2 -> mNavFragment3 = fragment as NavFragment3
            3 -> mNavFragment4 = fragment as NavFragment4
            4 -> mNavFragment5 = fragment as NavFragment5
        }
        return fragment
    }


    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> NavFragment1.newInstance(Bundle())
            1 -> NavFragment2.newInstance(Bundle())
            2 -> NavFragment3.newInstance(Bundle())
            3 -> NavFragment4.newInstance(Bundle())
            4 -> NavFragment5.newInstance(Bundle())
            else -> NavFragment1.newInstance(Bundle())
        }
    }

    override fun getCount(): Int {
        return mNumOfTabs
    }
}
