package com.jp.app.ui.sample.view

import android.app.Dialog
import com.jp.app.ui.BaseTest
import com.jp.app.ui.sample.SampleActivity
import com.jp.app.ui.sample.adapter.SampleAdapter
import kotlinx.android.synthetic.main.sample_fragment.*
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.robolectric.Robolectric
import org.robolectric.shadows.ShadowDialog

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
    fun checkFirstRowViewData() {

        mFragment!!.recycler_view.adapter = mFragment!!.getAdapter()!!

        val holder = mFragment!!.getAdapter()!!.onCreateViewHolder(mFragment!!.recycler_view, 0) as SampleAdapter.GameImageViewHolder
        mFragment!!.getAdapter()!!.onBindViewHolder(holder, 0)

        assertNotNull(holder)
        assertEquals("SampleDomain1", holder.getSampleComponentBinding().sample!!.title)

    }

    @Test
    fun checkOnClickFirstRowData() {
        mFragment!!.getAdapter()!!.getSampleAdapterCallBack()!!.sampleClicked(0)
        val dialog = ShadowDialog.getLatestDialog()
        assertThat<Dialog>("The dialog should be displayed", dialog, `is`<Any>(notNullValue()))
    }

}
