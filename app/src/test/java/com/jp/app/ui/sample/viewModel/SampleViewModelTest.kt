package com.jp.app.ui.sample.viewModel

import com.jp.app.ui.BaseTest
import com.jp.app.ui.sample.OptionsActivity
import com.jp.app.ui.sample.view.SampleFragment
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.robolectric.Robolectric
import org.robolectric.android.controller.ActivityController

class SampleViewModelTest : BaseTest() {

    private var mViewModel: SampleViewModel? = null

    private var mActivityController: ActivityController<OptionsActivity>? = null

    private var mActivity: OptionsActivity? = null

    private var mFragment: SampleFragment? = null

    @Before
    fun setup() {
        mActivityController = Robolectric.buildActivity(OptionsActivity::class.java)
        mActivityController!!.create().start().resume()
        mActivity = mActivityController!!.get()
        mFragment = mActivity!!.getCurrentFragment() as SampleFragment
        mViewModel = mFragment!!.getViewModel() as SampleViewModel
    }


    override fun controlViews() {
        assertNotNull(mActivity)
        assertNotNull(mFragment)
        assertNotNull(mViewModel)
    }

    @Test
    fun checkLoadSample() {
        assertEquals(3, mViewModel!!.mSampleViewMutableList.value!!.size)
    }
}
