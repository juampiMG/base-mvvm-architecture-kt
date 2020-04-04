package com.jp.app.ui.sample.viewModel

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import com.jp.app.common.viewModel.BaseViewModel
import com.jp.app.helper.subscribeSingle
import com.jp.app.model.SampleView
import com.jp.app.model.mapper.SampleViewMapper
import com.jp.app.ui.sample.adapter.SampleAdapter
import com.jp.domain.interactor.IGetSampleUseCase
import javax.inject.Inject

class SampleViewModel
@Inject
constructor() : BaseViewModel(), ISampleViewModel, SampleAdapter.SampleAdapterCallBack {
    @Inject
    lateinit var mGetSampleUseCase: IGetSampleUseCase

    @Inject
    lateinit var mSampleViewMapper: SampleViewMapper

    private val mSampleViewSelected = MutableLiveData<SampleView>()

    val mSampleViewObservableArrayList: ObservableList<SampleView> = ObservableArrayList()

    override fun sampleClicked(adapterPosition: Int) {
        mSampleViewSelected.value = mSampleViewObservableArrayList[adapterPosition]
    }

    fun getSampleViewSelected(): MutableLiveData<SampleView> {
        return mSampleViewSelected
    }

    fun addSamples(samples: List<SampleView>) {
        mSampleViewObservableArrayList.clear()
        mSampleViewObservableArrayList.addAll(samples)
    }

    override fun callGetSamplesUseCase() {
        addDisposable(mGetSampleUseCase.execute().subscribeSingle(
                onStart = {
                    isLoading(true)
                },
                onSuccess = {
                    isLoading(false)
                    addSamples(mSampleViewMapper.transform(it))
                },
                onError = { _, _, _ ->
                    isLoading(false)
                }
        ))
    }
}
