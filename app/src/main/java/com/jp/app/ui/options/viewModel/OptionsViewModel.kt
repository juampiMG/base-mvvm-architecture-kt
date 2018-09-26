package com.jp.app.ui.options.viewModel

import android.os.Bundle
import android.view.View
import com.jp.app.common.viewModel.BaseViewModel
import com.jp.app.ui.navigation.NavigationActivity
import com.jp.app.ui.sample.SampleActivity
import com.jp.app.utils.Constants.OPTIONS_TEST_BUNDLE
import javax.inject.Inject

class OptionsViewModel
@Inject
constructor() : BaseViewModel(), IOptionsViewModel {


    fun onClickSample(view : View) {
        val bundle = Bundle()
        bundle.putCharSequence(OPTIONS_TEST_BUNDLE, "SPEEDRUN")
        navigate(SampleActivity::class.java, bundle)
    }

    fun onClickNavigation(view : View) {
        navigate(NavigationActivity::class.java, null)
    }
}
