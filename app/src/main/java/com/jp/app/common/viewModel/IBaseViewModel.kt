package com.jp.app.common.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.os.Bundle
import com.jp.app.model.ShowErrorMessage

interface IBaseViewModel {
    fun getIsLoading(): LiveData<Boolean>
    fun showErrorMessage(): LiveData<ShowErrorMessage>
    fun navigateToActivity () : MutableLiveData<Pair<Class<*>, Bundle?>>
}
