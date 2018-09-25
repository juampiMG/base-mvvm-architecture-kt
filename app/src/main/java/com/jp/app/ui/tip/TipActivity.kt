package com.jp.app.ui.tip

import android.os.Bundle
import com.jp.app.R
import com.jp.app.common.BaseActivity
import com.jp.app.ui.tip.view.TipFragment
import com.jp.app.utils.NavigationUtils

class TipActivity: BaseActivity(), TipFragment.FragmentCallback  {

    private val LAYOUT_ID = R.layout.tip_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            mCurrentFragment = TipFragment.newInstance(Bundle())
            NavigationUtils.navigateToFragment(this, this.supportFragmentManager, mCurrentFragment!!, R.id.content, false)
        } else {
            mCurrentFragment = supportFragmentManager.findFragmentById(R.id.content)
        }

    }

     override fun getLayoutId(): Int {
        return LAYOUT_ID
    }

}
