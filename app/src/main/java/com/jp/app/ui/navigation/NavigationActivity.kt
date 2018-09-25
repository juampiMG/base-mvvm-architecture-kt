package com.jp.app.ui.navigation

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuItem
import com.jp.app.R
import com.jp.app.common.BaseActivity
import com.jp.app.components.BottomBarView
import com.jp.app.ui.navigation.adapter.BottomBarTabsAdapter
import com.jp.app.ui.navigation.view.*
import com.jp.app.ui.options.OptionsActivity
import com.jp.app.ui.tip.TipActivity
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

    override fun onBackPressed() {
        NavigationUtils.navigateToActivityNotAddStack(this, OptionsActivity::class.java, null)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.sample_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> manageBackPressed()
            R.id.flip_button -> {
                NavigationUtils.navigateToActivityAnimated (this, TipActivity::class.java, R.anim.rotate_in, R.anim.rotate_out)
            }
        }
        return true
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
