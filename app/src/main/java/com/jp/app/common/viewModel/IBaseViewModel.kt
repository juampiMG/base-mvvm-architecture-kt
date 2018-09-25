package com.jp.app.common.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.os.Bundle
import com.jp.app.model.ShowErrorMessage

interface IBaseViewModel {
    fun getIsLoading(): LiveData<Boolean>
    fun showErrorMessage(): LiveData<ShowErrorMessage>
    fun navigateToActivity () : MutableLiveData<Pair<Class<*>, Bundle?>>
}
