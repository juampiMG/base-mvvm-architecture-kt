package com.jp.app.common.view

import android.app.Activity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Build
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jp.app.common.viewModel.IBaseViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment<TViewDataBinding : ViewDataBinding, TCallback : IBaseFragmentCallback> : Fragment() {

    @Inject
    lateinit var mCallback: TCallback

    @Inject
    @VisibleForTesting
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    protected var mViewDataBinding: TViewDataBinding? = null

    private var mViewModel: IBaseViewModel? = null
    private var mRootView: View? = null

    private var mFragmentId: String? = null
    private var mLayoutId: Int = 0

    // =============== HasFragmentInjector =========================================================

    override fun onAttach(activity: Activity?) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            // Perform injection here before M, L (API 22) and below because onAttach(Context)
            // is not yet available at L.
            AndroidSupportInjection.inject(this)
        }
        super.onAttach(activity)
    }

    override fun onAttach(context: Context?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Perform injection here for M (API 23) due to deprecation of onAttach(Activity).
            AndroidSupportInjection.inject(this)
        }
        super.onAttach(context)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLayoutId = getLayoutId()
        mViewModel = getViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, mLayoutId, container, false)
        mRootView = mViewDataBinding!!.root
        return mRootView
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        /*
         * Bind the views here instead of in onViewCreated so that mView state changed listeners
         * are not invoked automatically without user interaction.
         *
         * If we bind before this method (e.g. onViewCreated), then any checked changed
         * listeners bound by ButterKnife will be invoked during fragment recreation (since
         * Android itself saves and restores the views' states. Take a look at this gist for a
         * concrete example: https://gist.github.com/vestrel00/982d585144423f728342787341fa001d
         *
         * The lifecycle order is as follows (same if added via xml or java or if retain
         * instance is true):
         *
         * onAttach
         * onCreateView
         * onViewCreated
         * onActivityCreated
         * onViewRestored
         * onStart
         * onResume
         *
         * Note that the onCreate (and other lifecycle events) are omitted on purpose. The
         * caveat to this approach is that views, listeners, and resources bound by
         * Butterknife will be null until onViewStatedRestored. Just be careful not to use any
         * objects bound using Butterknife before onViewRestored.
         *
         * Fragments that do not return a non-null View in onCreateView results in onViewCreated
         * and onViewRestored not being called. This means that Butterknife.bind will not get
         * called, which is completely fine because there is no View to bind. Furthermore, there is
         * no need to check if getView() returns null here because this lifecycle method only gets
         * called with a non-null View.
         */
        onViewLoaded(savedInstanceState, view)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding!!.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding!!.executePendingBindings()
    }

    open fun onViewLoaded(savedInstanceState: Bundle?, view: View?) {
        subscribers()
    }

    private fun subscribers() {
        loadingSubscribe()
        errorMessageSubscribe()
        navigationSubscribe ()
        subscribeToLiveData()
    }

    private fun navigationSubscribe() {
        getViewModel().navigateToActivity().observe(this, Observer { pair ->
            if (pair != null ) {
                mCallback.loadActivity(pair.first, pair.second)
            }
        })
    }

    private fun loadingSubscribe() {
        getViewModel().getIsLoading().observe(this, Observer { isLoading ->
            if (isLoading != null && isLoading) {
                mCallback.showLoading()
            } else {
                mCallback.hideLoading()
            }
        })

    }

    private fun errorMessageSubscribe() {
        getViewModel().showErrorMessage().observe(this, Observer { showErrorMessage ->
            if (showErrorMessage != null) {
                mCallback.showError(showErrorMessage.title!!, showErrorMessage.message!!, showErrorMessage.actionOnError!!)
            }
        })
    }

    fun getFragmentId(): String {
        val fragmentClass = (this as Any).javaClass
        mFragmentId = fragmentClass.name
        return mFragmentId!!
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): IBaseViewModel

    abstract fun subscribeToLiveData()

    abstract fun getBindingVariable(): Int


}
