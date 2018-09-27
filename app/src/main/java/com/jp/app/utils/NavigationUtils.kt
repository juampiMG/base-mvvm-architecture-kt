package com.jp.app.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.jp.app.common.view.BaseFragment

object NavigationUtils {

    @JvmStatic
    fun navigateToActivity(currentActivity: Activity, destActivity: Class<*>, bundle: Bundle?) {
        val intent = Intent(currentActivity, destActivity)
        if (bundle != null) intent.putExtras(bundle)
        currentActivity.startActivity(intent)
    }

    @JvmStatic
    fun navigateToActivityNotAddStack(currentActivity: Activity, destActivity: Class<*>, bundle: Bundle?) {
        val intent = Intent(currentActivity, destActivity)
        if (bundle != null) intent.putExtras(bundle)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        currentActivity.startActivity(intent)
    }


    @JvmStatic
    fun navigateToActivityAnimated(currentActivity: Activity, destActivity: Class<*>, enterAnim: Int, outAnim: Int) {
        val intent = Intent(currentActivity, destActivity)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        currentActivity.startActivity(intent)
        currentActivity.overridePendingTransition(enterAnim, outAnim)
    }


    @JvmStatic
    fun navigateToFragment(activity: Activity, supportFragmentManager: FragmentManager, fragment: Fragment, contentFrame: Int, addToBackStack: Boolean) {
        pushFragment(activity, fragment, supportFragmentManager, contentFrame, addToBackStack)
    }

    private fun getFragmentTag(fragment: Fragment): String {
        return (fragment as? BaseFragment<*, *>)?.getFragmentId()
                ?: (fragment as Any).javaClass.name
    }


    private fun pushFragment(activity: Activity, fragment: Fragment?, supportFragmentManager: FragmentManager, contentFrame: Int, addToBackStack: Boolean) {
        if (fragment == null) {
            return
        }

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        val tag = getFragmentTag(fragment)

        fragmentTransaction.replace(contentFrame, fragment, tag)

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(tag)
        }

        fragmentTransaction.commit()

        // Only calling executePendingTransactions() if no nested Fragment call
        if (contentFrame <= 0) {
            activity.fragmentManager.executePendingTransactions()
        }

    }
}
