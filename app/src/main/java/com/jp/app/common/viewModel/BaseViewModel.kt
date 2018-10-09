package com.jp.app.common.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.content.Context
import android.os.Bundle
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

    private val mNavigateToActivity = MutableLiveData<Pair<Class<*>, Bundle?>>()

    fun getContext(): Context {
        return mApplication.applicationContext
    }

    // =============== IBaseViewModel ==============================================================

    override fun getIsLoading(): LiveData<Boolean> {
        return mIsLoading
    }

    override fun showErrorMessage(): LiveData<ShowErrorMessage> {
        return mShowMessage
    }

    override fun navigateToActivity():  MutableLiveData<Pair<Class<*>, Bundle?>> {
        return mNavigateToActivity
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
        mIsLoading.value = visibility
    }

    fun navigate (activity: Class<*>, bundle: Bundle?) {
        val pair : Pair<Class<*>, Bundle?> = Pair(activity, bundle)
        mNavigateToActivity.value = pair
    }

    fun showErrorMessage(title: String, message: String, actionOnError: BaseActivity.ActionOnError) {
        val showMessage = ShowErrorMessage()
        showMessage.title = title
        showMessage.message = message
        showMessage.actionOnError = actionOnError
        mShowMessage.value = showMessage
    }
}
