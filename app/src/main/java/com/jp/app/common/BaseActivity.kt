package com.jp.app.common

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.jp.app.R
import com.jp.app.common.view.IBaseFragmentCallback
import com.jp.app.ui.sample.SampleActivity
import com.jp.app.utils.NavigationUtils
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.generic_loading.*
import kotlinx.android.synthetic.main.sample_activity.*
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector, IBaseFragmentCallback {
    private val DEFAULT_NUM_COUNT_BACK = 1

    private var mLayoutId: Int = 0

    protected var mCurrentFragment: Fragment? = null

    enum class ActionOnError {
        CLOSE, NOTHING
    }

    @Inject
    lateinit var mFragmentInjector: DispatchingAndroidInjector<Fragment>

    private var mCompositeDisposable: CompositeDisposable? = null
    private val mHandler = Handler()
    private var mExitToast: Toast? = null

    private var mDefaultCountBackToExit: Int = 0
    private var mCountBackToExit: Int = 0

    // =============== HasFragmentInjector =========================================================

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return mFragmentInjector
    }


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLayoutId = getLayoutId()
        setContentView(mLayoutId)

        AndroidInjection.inject(this)
        setUpBackPressValues()
    }

    // =============== Manage Views ================================================================

    abstract fun getLayoutId(): Int

    fun getCurrentFragment(): Fragment? {
        return mCurrentFragment
    }

    // =============== Manage BackPress ============================================================

    override fun onBackPressed() {
        manageBackPressed()
    }

    private fun setUpBackPressValues() {
        if (mDefaultCountBackToExit == 0) {
            mDefaultCountBackToExit = DEFAULT_NUM_COUNT_BACK
        }
        mCountBackToExit = mDefaultCountBackToExit
    }

    private val mRestartCountBackToExit = Runnable { mCountBackToExit = mDefaultCountBackToExit }

    private fun manageBackPressed() {
        if (fragmentManager.backStackEntryCount > 0) {
            super.onBackPressed()
        }
        if (this is SampleActivity) {
            if (mExitToast == null) {
                mExitToast = Toast.makeText(this, getString(R.string.count_back_exit_message), Toast.LENGTH_SHORT)
            }
            if (mCountBackToExit > 0) {
                mCountBackToExit--
                removeCallbacks()
                mHandler.postDelayed(mRestartCountBackToExit, 2000)
                mExitToast!!.show()
                fragmentManager.popBackStack()
            } else {
                mExitToast!!.cancel()
                super.onBackPressed()
            }

        } else {
            super.onBackPressed()
        }
    }

    private fun removeCallbacks() {
        mHandler.removeCallbacks(mRestartCountBackToExit)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> manageBackPressed()
        }
        return true
    }

    protected fun setUpFlipActivity(activityDes: Activity) {
       flip_button?.setOnClickListener { v -> NavigationUtils.navigateToActivityAnimated (this, activityDes, R.anim.rotate_in, R.anim.rotate_out)}
    }

    // =============== Manage Disposable ===========================================================

    fun getCompositeDisposable(): CompositeDisposable {
        if (mCompositeDisposable == null || mCompositeDisposable!!.isDisposed)
            mCompositeDisposable = CompositeDisposable()
        return mCompositeDisposable as CompositeDisposable
    }

    fun addDisposable(disposable: Disposable?) {
        if (disposable != null && mCompositeDisposable != null) {
            mCompositeDisposable!!.add(disposable)
        }
    }

    fun removeDisposable(disposable: Disposable?) {
        if (disposable != null) {
            if (!disposable.isDisposed) {
                disposable.dispose()
            }
            if (mCompositeDisposable != null) {
                mCompositeDisposable!!.remove(disposable)
            }
        }
    }

    fun removeAllDisposables() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable!!.clear()
        }
    }

    fun hasDisposables(): Boolean {
        return if (mCompositeDisposable != null) {
            mCompositeDisposable!!.size() > 0
        } else {
            false
        }
    }

    // =============== Generic Loading =============================================================

    override fun showLoading() {
        generic_loading?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        generic_loading?.visibility = View.GONE
    }

    // =============== ShowDialogs =================================================================

    override fun showError(title: String, message: String, action: ActionOnError) {
        showErrorDialog(title, message, action)
    }

    override fun showMessage(title: String, message: String) {
        showMessageDialog(title, message)
    }

    private fun showErrorDialog(title: String, message: String, action: ActionOnError) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
                .setTitle(title)
        builder.setPositiveButton(R.string.accept) { dialog, _ ->
            if (action == ActionOnError.CLOSE) {
                finish()
            }
            dialog.dismiss()
        }

        builder.create().show()
    }

    private fun showMessageDialog(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
                .setTitle(title)
        builder.setPositiveButton(R.string.accept) { dialog, _ -> dialog.dismiss() }

        builder.create().show()
    }
}
