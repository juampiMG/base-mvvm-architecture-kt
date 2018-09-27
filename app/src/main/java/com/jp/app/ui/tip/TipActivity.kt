package com.jp.app.ui.tip

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.jp.app.R
import com.jp.app.common.BaseActivity
import com.jp.app.ui.navigation.NavigationActivity
import com.jp.app.ui.tip.view.TipFragment
import com.jp.app.utils.NavigationUtils

class TipActivity : BaseActivity(), TipFragment.FragmentCallback {

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.sample_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> manageBackPressed()
            R.id.flip_button -> {
                NavigationUtils.navigateToActivityAnimated(this, NavigationActivity::class.java, R.anim.rotate_in, R.anim.rotate_out)
            }
        }
        return true
    }

    override fun getLayoutId(): Int {
        return LAYOUT_ID
    }

}
