package com.jp.data.network.gateway.retrofit

import com.jp.data.entity.sample.ResultSampleEntity
import com.jp.data.network.gateway.IAppGateway
import com.jp.data.preferences.URLPreferenceManager
import com.jp.data.network.gateway.retrofit.service.IRestServices
import io.reactivex.Single

class AppGateway(private val mService: IRestServices, private val urlPreferenceManager: URLPreferenceManager) : IAppGateway {

    override fun getSamples(): Single<ResultSampleEntity> {
        return mService.getSamples(urlPreferenceManager.getURL() + "games")
    }
}