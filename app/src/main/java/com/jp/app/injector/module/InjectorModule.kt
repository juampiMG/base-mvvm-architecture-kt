package com.jp.app.injector.module

import com.jp.app.injector.scope.PerActivity
import com.jp.app.ui.navigation.NavigationActivity
import com.jp.app.ui.navigation.NavigationActivityModule
import com.jp.app.ui.options.OptionsActivity
import com.jp.app.ui.options.OptionsActivityModule
import com.jp.app.ui.sample.SampleActivity
import com.jp.app.ui.sample.SampleActivityModule
import com.jp.app.ui.tip.TipActivity
import com.jp.app.ui.tip.TipActivityModule
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

    @PerActivity
    @ContributesAndroidInjector(modules = [TipActivityModule::class])
    internal abstract fun tipActivity(): TipActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [OptionsActivityModule::class])
    internal abstract fun optionsActivity(): OptionsActivity

}
