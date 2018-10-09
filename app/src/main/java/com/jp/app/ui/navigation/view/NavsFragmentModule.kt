package com.jp.app.ui.navigation.view

import androidx.lifecycle.ViewModelProvider
import androidx.fragment.app.Fragment
import com.jp.app.common.ViewModelProviderFactory
import com.jp.app.injector.scope.PerFragment
import com.jp.app.ui.navigation.viewModel.NavViewModel
import dagger.Binds
import dagger.Module

@Module
abstract class NavsFragmentModule {
    @Binds
    @PerFragment
    internal abstract fun nav1Fragment(fragment: NavFragment1): Fragment

    @Binds
    @PerFragment
    internal abstract fun nav2Fragment(fragment: NavFragment2): Fragment

    @Binds
    @PerFragment
    internal abstract fun nav3Fragment(fragment: NavFragment3): Fragment

    @Binds
    @PerFragment
    internal abstract fun nav4Fragment(fragment: NavFragment4): Fragment

    @Binds
    @PerFragment
    internal abstract fun nav5Fragment(fragment: NavFragment5): Fragment

    @Binds
    @PerFragment
    internal abstract fun provideNavViewModel(navViewModel: ViewModelProviderFactory<NavViewModel>): ViewModelProvider.Factory

}
