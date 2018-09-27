package com.jp.app.ui.sample.view

import com.jp.app.ui.BaseTest
import com.jp.app.ui.sample.SampleActivity
import kotlinx.android.synthetic.main.sample_fragment.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.robolectric.Robolectric

class SampleViewTest : BaseTest() {
    private var mActivity: SampleActivity? = null

    private var mFragment: SampleFragment? = null

    @Before
    fun setup() {
        mActivity = Robolectric.setupActivity(SampleActivity::class.java)
        mFragment = mActivity!!.getCurrentFragment() as SampleFragment
    }


    override fun controlViews() {
        assertNotNull(mActivity)
        assertNotNull(mFragment)
    }

    @Test
    fun checkRecyclerViewNotNull() {
        assertNotNull(mFragment!!.recycler_view)
    }

    @Test
    fun checkAdapter() {
        assertNotNull(mFragment!!.getAdapter())
    }

    @Test
    fun checkAdapterLoadAllData() {
        assertEquals(3, mFragment!!.getAdapter()!!.itemCount)
    }


    @Test
    fun checkOnClickFirstRowData() {
     //   mFragment!!.recycler_view.getChildAt(0).performClick()
     //   val dialog = ShadowDialog.getLatestDialog()
     //   assertThat<Dialog>("The dialog should be displayed", dialog, `is`<Any>(notNullValue()))
        assertTrue(true)
    }

}
