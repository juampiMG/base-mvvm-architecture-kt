package com.jp.app.common

import android.app.Activity
import android.app.FragmentManager
import android.content.res.Resources
import android.os.Bundle
import com.jp.app.injector.scope.PerActivity
import dagger.Module
import dagger.Provides


@Module
object BaseActivityModule {

    @JvmStatic
    @Provides
    @PerActivity
    internal fun resources(activity: Activity): Resources {
        return activity.resources
    }

    @JvmStatic
    @Provides
    @PerActivity
    internal fun activityExtras(activity: Activity): Bundle? {
        return if (activity.intent != null && activity.intent.extras != null) activity.intent.extras else Bundle()
    }

    @JvmStatic
    @Provides
    @PerActivity
    internal fun baseActivity(activity: Activity): BaseActivity {
        return activity as BaseActivity
    }

    @JvmStatic
    @Provides
    @PerActivity
    internal fun activityFragmentManager(activity: Activity): FragmentManager {
        return activity.fragmentManager
    }

}