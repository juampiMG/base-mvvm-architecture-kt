package com.jp.app.ui.tip.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.jp.app.BR
import com.jp.app.R
import com.jp.app.common.view.BaseFragment
import com.jp.app.common.view.IBaseFragmentCallback
import com.jp.app.common.viewModel.BaseViewModel
import com.jp.app.databinding.TipFragmentBinding
import com.jp.app.ui.tip.viewModel.TipViewModel
import kotlinx.android.synthetic.main.tip_fragment.*

class TipFragment : BaseFragment<TipFragmentBinding, TipFragment.FragmentCallback>() {


    val LAYOUT_ID = R.layout.tip_fragment

    private var mTipViewModel: TipViewModel? = null

    companion object {
        fun newInstance(bundle: Bundle?) = TipFragment().apply {
            arguments = bundle ?: Bundle()
        }
    }


    interface FragmentCallback : IBaseFragmentCallback

    override fun getViewModel(): BaseViewModel {
        mTipViewModel = ViewModelProviders.of(this, mViewModelFactory).get(TipViewModel::class.java)
        return mTipViewModel as TipViewModel
    }

    override fun getLayoutId(): Int {
        return LAYOUT_ID
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun subscribeToLiveData() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAnimationTip()
    }

    private fun setUpAnimationTip() {
        animation_view.setAnimation(R.raw.sd_anim_light_bulb)
    }

}
