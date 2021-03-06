package com.jp.app.injector.component

import com.jp.app.SampleApplication
import com.jp.app.common.BaseApplicationModule
import com.jp.app.injector.module.InjectorModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, BaseApplicationModule::class, InjectorModule::class])
interface ApplicationComponent : AndroidInjector<SampleApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<SampleApplication>()

}
