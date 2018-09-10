package com.jp.app.common.view

import com.jp.app.common.BaseActivity

interface IBaseFragmentCallback {
    fun showLoading()

    fun hideLoading()

    fun showError(title: String, message: String, action: BaseActivity.actionOnError)

    fun showMessage(title: String, message: String)

}