package com.jp.app.ui.sample.viewModel

import android.arch.lifecycle.MutableLiveData
import com.jp.app.common.viewModel.IBaseViewModel
import com.jp.app.model.SampleView

interface ISampleViewModel : IBaseViewModel {

    fun getSamples(): MutableLiveData<List<SampleView>>

    fun addSamples(samples: List<SampleView>)

    fun callGetSamplesUseCase()
}