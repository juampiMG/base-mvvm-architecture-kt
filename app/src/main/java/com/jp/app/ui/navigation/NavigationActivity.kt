package com.jp.app.ui.navigation

import android.os.Bundle
import android.support.v4.view.ViewPager
import com.jp.app.R
import com.jp.app.common.BaseActivity
import com.jp.app.components.BottomBarView
import com.jp.app.ui.navigation.adapter.BottomBarTabsAdapter
import com.jp.app.ui.navigation.view.*
import com.jp.app.ui.sample.SampleActivity
import com.jp.app.utils.NavigationUtils
import kotlinx.android.synthetic.main.navigation_activity.*

class NavigationActivity : BaseActivity(),
        BottomBarView.BottomBarViewCallbacks,
        NavFragment1.FragmentCallback,
        NavFragment2.FragmentCallback,
        NavFragment3.FragmentCallback,
        NavFragment4.FragmentCallback,
        NavFragment5.FragmentCallback {

    private var mAdapter: BottomBarTabsAdapter? = null

    private val LAYOUT_ID = R.layout.navigation_activity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            mCurrentFragment = NavFragment1.newInstance(Bundle())
            NavigationUtils.navigateToFragment(this, this.supportFragmentManager, mCurrentFragment!!, R.id.view_pager, false)
        } else {
            mCurrentFragment = supportFragmentManager.findFragmentById(R.id.view_pager)
        }
        setUpFlipActivity(SampleActivity())
    }

    override fun onStart() {
        super.onStart()
        setUpViewPager()
    }

    override fun getLayoutId(): Int {
        return LAYOUT_ID
    }

    override fun onTabSelected(position: Int) {
        view_pager.setCurrentItem(position, false)

        bottom_view.handleCheckedTab()
    }

    fun setUpViewPager() {
        mAdapter = BottomBarTabsAdapter(supportFragmentManager, 5)
        view_pager.adapter = mAdapter
        view_pager.addOnPageChangeListener(onPageChangeListener)
        view_pager.offscreenPageLimit = 4
        bottom_view.also {
            it.setViewPager(view_pager, mIconResArray)
            it.mCallbacks = this
        }
    }

    override fun onTabReSelected(position: Int) {
    }

    companion object {

        private val mIconResArray = intArrayOf(
                R.drawable.bottom_navigation_item_home,
                R.drawable.bottom_navigation_item_products,
                R.drawable.bottom_navigation_item_cart,
                R.drawable.bottom_navigation_item_info,
                R.drawable.bottom_navigation_item_profile
        )
    }


    private val onPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

        }

        override fun onPageSelected(position: Int) {
            mCurrentFragment = mAdapter!!.getItem(position)
        }

        override fun onPageScrollStateChanged(state: Int) {

        }
    }


}
