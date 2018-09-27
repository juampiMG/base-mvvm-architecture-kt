package com.jp.app.ui.options

import android.app.Activity
import com.jp.app.common.BaseActivityModule
import com.jp.app.injector.scope.PerActivity
import com.jp.app.injector.scope.PerFragment
import com.jp.app.ui.options.view.OptionsFragment
import com.jp.app.ui.options.view.OptionsFragmentModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [BaseActivityModule::class])
abstract class OptionsActivityModule {

    @Binds
    @PerActivity
    internal abstract fun activity(activity: OptionsActivity): Activity

    @Binds
    @PerActivity
    internal abstract fun fragmentCallback(activity: OptionsActivity): OptionsFragment.FragmentCallback


    @PerFragment
    @ContributesAndroidInjector(modules = [OptionsFragmentModule::class])
    internal abstract fun fragmentInjector(): OptionsFragment

}
