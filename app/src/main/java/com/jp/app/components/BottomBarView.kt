package com.jp.app.components

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import android.util.AttributeSet
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.jp.app.R
import com.jp.app.common.BaseComponentView
import kotlinx.android.synthetic.main.bottom_bar_view.view.*

class BottomBarView : BaseComponentView, BottomBarItemView.OnCheckedChangeListener, BottomBarItemView.OnReCheckedListener {

    interface BottomBarViewCallbacks {
        fun onTabSelected(position: Int)
        fun onTabReSelected(position: Int)
    }

    private lateinit var mRootView: LinearLayout
    private var mIconsArray: IntArray? = null
    private var mViewPager: ViewPager? = null
    private var mItemsArray: SparseArray<BottomBarItemView> = SparseArray()

    var mCallbacks: BottomBarViewCallbacks? = null

    private var mLastChecked = 0

    override val layoutId: Int
        get() = R.layout.bottom_bar_view

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun loadAttributes(context: Context, attrs: AttributeSet?) {

    }

    override fun bindViews() {
        mRootView = findViewById(R.id.root)
    }

    override fun loadData() {

    }

    override fun onCheckedChanged(itemView: BottomBarItemView, isChecked: Boolean) {
        if (isChecked) mCallbacks?.onTabSelected(itemView.tag as Int)
    }

    override fun onReChecked(itemView: BottomBarItemView) {
        mCallbacks?.onTabReSelected(itemView.tag as Int)
    }

    fun setBadgeCountAt(position: Int, count: Int) {
        mItemsArray.valueAt(position).setCounter(count)
    }

    fun setViewPager(viewPager: ViewPager, iconsArray: IntArray) {
        mViewPager = viewPager
        mIconsArray = iconsArray
        addItems()

        mViewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageSelected(position: Int) {
                mLastChecked = position
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
        })
    }

    fun handleCheckedTab() {
        for (i in 0 until mItemsArray.size()) {
            mItemsArray[i].setChecked(i == mLastChecked, false)
        }
    }

    fun setCurrentTab(position: Int) {
        mItemsArray[position].setChecked(true, true)
    }

    fun showHideShadow(show: Boolean) {
        shadow_view.visibility = if (show) View.VISIBLE else View.INVISIBLE
    }

    private fun addItems() {
        val params = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT)
        params.weight = 1f

        val numItems = mViewPager?.adapter?.count ?: 0

        for (i in 0 until numItems) {
            val itemView = BottomBarItemView(context).also {
                it.setIcon(getIcon(i))
                it.layoutParams = params
                it.setAnimateCounter(true)
                it.tag = i
                it.isChecked = i == 0
                it.setOnCheckedChangeListener(this)
                it.setOnReCheckedListener(this)
                it.setCounterColor(ContextCompat.getColor(context, R.color.cyan))
                it.setCounterBackground(null)

                if (i == 2){
                    it.mImageView.scaleX = 1.4f
                    it.mImageView.scaleY = 1.4f
                }
            }

            mRootView.addView(itemView)
            mItemsArray.put(i, itemView)
        }
    }

    private fun getIcon(position: Int): Int {
        return if (mIconsArray == null || position >= mIconsArray!!.size) {
            R.drawable.bottom_navigation_item_home
        } else mIconsArray!![position]
    }
}
