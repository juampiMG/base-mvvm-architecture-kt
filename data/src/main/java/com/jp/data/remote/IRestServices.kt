package com.jp.data.remote

import com.jp.data.entity.sample.ResultSampleEntity
import io.reactivex.Single

interface IRestServices {

    fun getSamples(): Single<ResultSampleEntity>
}