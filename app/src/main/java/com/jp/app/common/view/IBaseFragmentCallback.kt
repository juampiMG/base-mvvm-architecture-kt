package com.jp.app.common.view

import com.jp.app.common.BaseActivity

interface IBaseFragmentCallback {
    fun showLoading()

    fun hideLoading()

    fun showError(title: String, message: String, action: BaseActivity.ActionOnError)

    fun showMessage(title: String, message: String)

}
