package com.jp.data.remote

import com.jp.data.Constants
import com.jp.data.entity.sample.ResultSampleEntity
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RestServices
@Inject
constructor() : IRestServices{

    override fun getSamples(): Single<ResultSampleEntity> {
        return Rx2AndroidNetworking.get(Constants.BASE_URL_SAMPLES)
                .build()
                .getObjectSingle(ResultSampleEntity::class.java)
    }
}