package com.jp.app.common

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

class ViewModelProviderFactory<V : Any>
@Inject
constructor(viewModel: V): ViewModelProvider.Factory {

    private val mViewModel:  V = viewModel

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(mViewModel.javaClass)) {
            return mViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
