package com.jp.app.ui.sample

import android.os.Bundle
import com.jp.app.R
import com.jp.app.common.BaseActivity
import com.jp.app.model.SampleView
import com.jp.app.ui.sample.view.SampleFragment
import com.jp.app.utils.Constants
import com.jp.app.utils.NavigationUtils
import kotlinx.android.synthetic.main.toolbar_view.*

class SampleActivity: BaseActivity(), SampleFragment.FragmentCallback  {

    private val LAYOUT_ID = R.layout.sample_activity

    private var mTitle: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            mCurrentFragment = SampleFragment.newInstance(Bundle())
            NavigationUtils.navigateToFragment(this, this.supportFragmentManager, mCurrentFragment!!, R.id.content, false)
        } else {
            mCurrentFragment = supportFragmentManager.findFragmentById(R.id.content)
        }

        mTitle = mExtras.getString(Constants.OPTIONS_TEST_BUNDLE)

    }

    override fun onStart() {
        super.onStart()
        setViews()
    }

    private fun setViews() {
        toolbar_title?.text = mTitle
    }

     override fun getLayoutId(): Int {
        return LAYOUT_ID
    }

    override fun loadSampleInfo(sample: SampleView) {
        showMessage(getString(R.string.information), String.format(getString(R.string.message_on_click), sample.title))
    }
}
