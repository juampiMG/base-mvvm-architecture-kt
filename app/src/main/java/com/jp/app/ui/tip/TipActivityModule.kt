package com.jp.app.ui.tip

import android.app.Activity
import com.jp.app.common.BaseActivityModule
import com.jp.app.injector.scope.PerActivity
import com.jp.app.injector.scope.PerFragment
import com.jp.app.ui.tip.view.TipFragment
import com.jp.app.ui.tip.view.TipFragmentModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [BaseActivityModule::class])
abstract class TipActivityModule {

    @Binds
    @PerActivity
    internal abstract fun activity(activity: TipActivity): Activity

    @Binds
    @PerActivity
    internal abstract fun fragmentCallback(activity: TipActivity): TipFragment.FragmentCallback


    @PerFragment
    @ContributesAndroidInjector(modules = [TipFragmentModule::class])
    internal abstract fun fragmentInjector(): TipFragment

}
