package com.jp.app.common.viewModel

import android.arch.lifecycle.LiveData
import com.jp.app.model.ShowErrorMessage

interface IBaseViewModel {
    fun getIsLoading(): LiveData<Boolean>
    fun showErrorMessage(): LiveData<ShowErrorMessage>
}