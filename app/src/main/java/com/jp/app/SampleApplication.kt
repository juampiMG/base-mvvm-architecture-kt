package com.jp.app

import android.app.Activity
import android.os.Build
import androidx.multidex.MultiDexApplication
import androidx.appcompat.app.AppCompatDelegate
import com.jp.app.injector.component.DaggerApplicationComponent
import com.jp.data.network.gateway.retrofit.authenticator.IRefreshAuthenticator
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.reactivex.Single
import javax.inject.Inject

class SampleApplication : MultiDexApplication(), HasActivityInjector, IRefreshAuthenticator {

    @Inject
    internal lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        initCompatVector()
        initDaggerApplicationComponent()
    }

    // =============== HasActivityInjector =========================================================

    override fun activityInjector(): AndroidInjector<Activity>? {
        return activityInjector
    }

    // =============== Support methods =============================================================

    private fun initCompatVector() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
    }

    private fun initDaggerApplicationComponent() {
        DaggerApplicationComponent.builder().create(this).inject(this)
    }

    override fun userOauthRefreshedBearerToken(): Single<String> {
        return Single.just("Used to log again the user and get the proper Token")
    }

    override fun logOut() {
    }

}
