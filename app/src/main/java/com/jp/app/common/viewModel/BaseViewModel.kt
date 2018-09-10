package com.jp.app.common.viewModel

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.jp.app.common.BaseActivity
import com.jp.app.model.ShowErrorMessage
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BaseViewModel : ViewModel(), IBaseViewModel {

    @Inject
    lateinit var mApplication: Application

    private val mIsLoading = MutableLiveData<Boolean>()

    private val mShowMessage = MutableLiveData<ShowErrorMessage>()

    private val mCompositeDisposable: CompositeDisposable? =  CompositeDisposable()

    fun getContext(): Context {
        return mApplication!!.applicationContext
    }

    // =============== IBaseViewModel ==============================================================

    override fun getIsLoading(): LiveData<Boolean> {
        return mIsLoading
    }

    override fun showErrorMessage(): LiveData<ShowErrorMessage> {
        return mShowMessage
    }

    // =============== CompositeDispoable ==========================================================

    override fun onCleared() {
        mCompositeDisposable!!.dispose()
        super.onCleared()
    }

    fun addDisposable(disposable: Disposable?) {
        if (disposable != null && mCompositeDisposable != null) {
            mCompositeDisposable.add(disposable)
        }
    }

    fun isLoading(visibility: Boolean) {
        mIsLoading.setValue(visibility)
    }

    fun showErrorMessage(title: String, message: String, actionOnError: BaseActivity.actionOnError) {
        val showMessage = ShowErrorMessage()
        showMessage.title = title
        showMessage.message = message
        showMessage.actionOnError = actionOnError
        mShowMessage.setValue(showMessage)
    }
}