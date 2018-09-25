package com.jp.app.common

import android.app.Activity
import android.app.FragmentManager
import android.os.Bundle
import com.jp.app.injector.scope.PerActivity
import dagger.Module
import dagger.Provides


@Module
object BaseActivityModule {

    @JvmStatic
    @Provides
    @PerActivity
    internal fun activityExtras(activity: Activity): Bundle {
        return if (activity.intent != null && activity.intent.extras != null) activity.intent.extras else Bundle()
    }

    @JvmStatic
    @Provides
    @PerActivity
    internal fun activityFragmentManager(activity: Activity): FragmentManager {
        return activity.fragmentManager
    }

}
