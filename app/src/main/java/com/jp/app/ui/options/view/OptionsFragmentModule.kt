package com.jp.app.ui.options.view

import androidx.lifecycle.ViewModelProvider
import androidx.fragment.app.Fragment
import com.jp.app.common.ViewModelProviderFactory
import com.jp.app.injector.scope.PerFragment
import com.jp.app.ui.options.viewModel.OptionsViewModel
import dagger.Binds
import dagger.Module

@Module
abstract class OptionsFragmentModule {
    @Binds
    @PerFragment
    internal abstract fun fragment(fragment: OptionsFragment): Fragment

    @Binds
    @PerFragment
    internal abstract fun provideOptionsViewModel(optionsViewModel: ViewModelProviderFactory<OptionsViewModel>): ViewModelProvider.Factory

}
