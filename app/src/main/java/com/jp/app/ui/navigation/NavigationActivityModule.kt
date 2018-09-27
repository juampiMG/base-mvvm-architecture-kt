package com.jp.app.ui.navigation

import android.app.Activity
import com.jp.app.common.BaseActivityModule
import com.jp.app.injector.scope.PerActivity
import com.jp.app.injector.scope.PerFragment
import com.jp.app.ui.navigation.view.*
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module(includes = [BaseActivityModule::class])
abstract class NavigationActivityModule {

    @Binds
    @PerActivity
    internal abstract fun activity(activity: NavigationActivity): Activity

    @Binds
    @PerActivity
    internal abstract fun nav1FragmentCallback(activity: NavigationActivity): NavFragment1.FragmentCallback

    @Binds
    @PerActivity
    internal abstract fun nav2FragmentCallback(activity: NavigationActivity): NavFragment2.FragmentCallback

    @Binds
    @PerActivity
    internal abstract fun nav3FragmentCallback(activity: NavigationActivity): NavFragment3.FragmentCallback

    @Binds
    @PerActivity
    internal abstract fun nav4FragmentCallback(activity: NavigationActivity): NavFragment4.FragmentCallback

    @Binds
    @PerActivity
    internal abstract fun nav5FragmentCallback(activity: NavigationActivity): NavFragment5.FragmentCallback


    @PerFragment
    @ContributesAndroidInjector(modules = [NavsFragmentModule::class])
    internal abstract fun fragmentNav1Injector(): NavFragment1

    @PerFragment
    @ContributesAndroidInjector(modules = [NavsFragmentModule::class])
    internal abstract fun fragmentNav2Injector(): NavFragment2

    @PerFragment
    @ContributesAndroidInjector(modules = [NavsFragmentModule::class])
    internal abstract fun fragmentNav3Injector(): NavFragment3

    @PerFragment
    @ContributesAndroidInjector(modules = [NavsFragmentModule::class])
    internal abstract fun fragmentNav4Injector(): NavFragment4

    @PerFragment
    @ContributesAndroidInjector(modules = [NavsFragmentModule::class])
    internal abstract fun fragmentNav5Injector(): NavFragment5

}
