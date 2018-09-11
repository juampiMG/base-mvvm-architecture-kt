package com.jp.app.ui.sample

import android.os.Bundle
import com.jp.app.R
import com.jp.app.common.BaseActivity
import com.jp.app.model.SampleView
import com.jp.app.ui.sample.view.SampleFragment
import com.jp.app.utils.NavigationUtils

class SampleActivity: BaseActivity(), SampleFragment.FragmentCallback  {

    private val LAYOUT_ID = R.layout.sample_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            mCurrentFragment = SampleFragment.newInstance(Bundle())
            NavigationUtils.navigateToFragment(this, this.supportFragmentManager, mCurrentFragment!!, R.id.content, false)
        } else {
            mCurrentFragment = supportFragmentManager.findFragmentById(R.id.content)
        }
    }

     override fun getLayoutId(): Int {
        return LAYOUT_ID
    }

    override fun loadSampleInfo(sample: SampleView) {
        showMessage(getString(R.string.information), String.format(getString(R.string.message_on_click), sample.title))
    }
}