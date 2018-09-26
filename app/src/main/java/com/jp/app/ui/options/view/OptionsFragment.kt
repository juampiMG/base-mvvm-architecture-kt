package com.jp.app.ui.options.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.jp.app.BR
import com.jp.app.R
import com.jp.app.common.view.BaseFragment
import com.jp.app.common.view.IBaseFragmentCallback
import com.jp.app.common.viewModel.BaseViewModel
import com.jp.app.databinding.OptionsFragmentBinding
import com.jp.app.ui.options.viewModel.OptionsViewModel

class OptionsFragment : BaseFragment<OptionsFragmentBinding, OptionsFragment.FragmentCallback>() {


    val LAYOUT_ID = R.layout.options_fragment

    private var mOptionViewModel: OptionsViewModel? = null

    companion object {
        fun newInstance(bundle: Bundle?) = OptionsFragment().apply {
            arguments = bundle ?: Bundle()
        }
    }


    interface FragmentCallback : IBaseFragmentCallback

    override fun getViewModel(): BaseViewModel {
        mOptionViewModel = ViewModelProviders.of(this, mViewModelFactory).get(OptionsViewModel::class.java)
        return mOptionViewModel as OptionsViewModel
    }

    override fun getLayoutId(): Int {
        return LAYOUT_ID
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun subscribeToLiveData() {

    }

}
