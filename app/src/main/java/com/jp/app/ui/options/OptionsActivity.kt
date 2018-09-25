package com.jp.app.ui.options

import android.os.Bundle
import com.jp.app.R
import com.jp.app.common.BaseActivity
import com.jp.app.ui.options.view.OptionsFragment
import com.jp.app.utils.NavigationUtils

class OptionsActivity : BaseActivity(), OptionsFragment.FragmentCallback {

    private val LAYOUT_ID = R.layout.options_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            mCurrentFragment = OptionsFragment.newInstance(Bundle())
            NavigationUtils.navigateToFragment(this, this.supportFragmentManager, mCurrentFragment!!, R.id.content, false)
        } else {
            mCurrentFragment = supportFragmentManager.findFragmentById(R.id.content)
        }

    }

    override fun getLayoutId(): Int {
        return LAYOUT_ID
    }

}
