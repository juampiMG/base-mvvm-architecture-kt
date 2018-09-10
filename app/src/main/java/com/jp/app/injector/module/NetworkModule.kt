package com.jp.app.injector.module

import com.jp.data.remote.IRestServices
import com.jp.data.remote.RestServices
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class NetworkModule {
    @Binds
    @Singleton
    internal abstract fun restServices(restServices: RestServices): IRestServices
}
