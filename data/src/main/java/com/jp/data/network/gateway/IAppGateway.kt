package com.jp.data.network.gateway

import com.jp.data.entity.sample.ResultSampleEntity
import io.reactivex.Single


interface IAppGateway {
    fun getSamples(): Single<ResultSampleEntity>
}