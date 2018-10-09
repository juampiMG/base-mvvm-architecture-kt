package com.jp.app.ui.tip.view

import androidx.lifecycle.ViewModelProvider
import androidx.fragment.app.Fragment
import com.jp.app.common.ViewModelProviderFactory
import com.jp.app.injector.scope.PerFragment
import com.jp.app.ui.tip.viewModel.TipViewModel
import dagger.Binds
import dagger.Module

@Module
abstract class TipFragmentModule {
    @Binds
    @PerFragment
    internal abstract fun fragment(fragment: TipFragment): Fragment

    @Binds
    @PerFragment
    internal abstract fun provideTipViewModel(tipViewModel: ViewModelProviderFactory<TipViewModel>): ViewModelProvider.Factory

}
