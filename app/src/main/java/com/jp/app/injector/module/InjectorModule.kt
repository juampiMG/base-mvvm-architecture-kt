package com.jp.app.injector.module

import com.jp.app.injector.scope.PerActivity
import com.jp.app.ui.navigation.NavigationActivity
import com.jp.app.ui.navigation.NavigationActivityModule
import com.jp.app.ui.sample.SampleActivity
import com.jp.app.ui.sample.SampleActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class InjectorModule {
    @PerActivity
    @ContributesAndroidInjector(modules = [SampleActivityModule::class])
    internal abstract fun sampleActivity(): SampleActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [NavigationActivityModule::class])
    internal abstract fun navigationActivity(): NavigationActivity

}
