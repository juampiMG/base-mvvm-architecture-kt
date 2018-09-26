package com.jp.app.common

import android.content.Context
import com.jp.app.R
import io.reactivex.SingleObserver
import io.reactivex.annotations.NonNull

abstract class BaseSingleObserver<T>(context: Context) : SingleObserver<T> {
    private val mContext: Context = context

    override fun onError(@NonNull e: Throwable) {
        handleError(e)
    }

    protected abstract fun onError(code: Int, title: String, description: String)

    private fun handleError(throwable: Throwable) {
        val code = 0
        val title = mContext.getString(R.string.oh_hell)
        val message = mContext.getString(R.string.default_error)

        onError(code, title, message)
    }
}
