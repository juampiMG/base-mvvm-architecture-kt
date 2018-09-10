package com.jp.app.ui.sample.view

import android.arch.lifecycle.ViewModelProvider
import android.support.v4.app.Fragment
import com.jp.app.common.ViewModelProviderFactory
import com.jp.app.injector.scope.PerFragment
import com.jp.app.ui.sample.viewModel.SampleViewModel
import dagger.Binds
import dagger.Module

@Module
abstract class SampleFragmentModule {
    @Binds
    @PerFragment
    internal abstract fun fragment(fragment: SampleFragment): Fragment

    @Binds
    @PerFragment
    internal abstract fun provideSampleViewModel(sampleViewModel: ViewModelProviderFactory<SampleViewModel>): ViewModelProvider.Factory

}