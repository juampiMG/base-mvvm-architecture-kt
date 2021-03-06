package com.jp.app.ui.navigation.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import com.jp.app.BR
import com.jp.app.R
import com.jp.app.common.view.BaseFragment
import com.jp.app.common.view.IBaseFragmentCallback
import com.jp.app.common.viewModel.IBaseViewModel
import com.jp.app.databinding.NavThreeFragmentBinding
import com.jp.app.ui.navigation.viewModel.NavViewModel

class NavFragment3 : BaseFragment<NavThreeFragmentBinding, NavFragment3.FragmentCallback>() {

    val LAYOUT_ID = R.layout.nav_three_fragment

    private var mNavViewModel: NavViewModel? = null

    companion object {
        fun newInstance(bundle: Bundle?) = NavFragment3().apply {
            arguments = bundle ?: Bundle()
        }
    }


    interface FragmentCallback : IBaseFragmentCallback {
    }

    override fun getLayoutId(): Int {
        return LAYOUT_ID
    }

    override fun getViewModel(): IBaseViewModel {
        mNavViewModel = ViewModelProviders.of(this, mViewModelFactory).get(NavViewModel::class.java)
        mNavViewModel!!.isLoading(false)
        return mNavViewModel as NavViewModel
    }

    override fun subscribeToLiveData() {
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }
}
