package com.jp.app.ui.options.viewModel

import android.view.View
import com.jp.app.common.viewModel.BaseViewModel
import com.jp.app.ui.navigation.NavigationActivity
import com.jp.app.ui.sample.SampleActivity
import javax.inject.Inject

class OptionsViewModel
@Inject
constructor() : BaseViewModel(), IOptionsViewModel {


    fun onClickSample(view: View) {
        navigate(SampleActivity())
    }

    fun onClickNavigation(view: View) {
        navigate(NavigationActivity())
    }
}
