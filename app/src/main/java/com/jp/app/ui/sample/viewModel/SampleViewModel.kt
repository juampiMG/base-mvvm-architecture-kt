package com.jp.app.ui.sample.viewModel

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.jp.app.common.BaseActivity
import com.jp.app.common.BaseSingleObserver
import com.jp.app.common.viewModel.BaseViewModel
import com.jp.app.model.SampleView
import com.jp.app.model.mapper.SampleViewMapper
import com.jp.app.ui.sample.adapter.SampleAdapter
import com.jp.domain.interactor.IGetSampleUseCase
import com.jp.domain.model.SampleDomain
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
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

    internal var mSampleDomain: List<SampleDomain> = ArrayList()


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
        isLoading(true)
        mGetSampleUseCase.execute().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseSingleObserver<List<SampleDomain>>(getContext()) {

                    override fun onSubscribe(d: Disposable) {
                        addDisposable(d)
                    }

                    override fun onSuccess(sample: List<SampleDomain>) {
                        isLoading(false)
                        if (sample != null) {
                            mSampleDomain = sample
                            addSamples(mSampleViewMapper.transform(sample))
                        }
                    }

                    override fun onError(code: Int, title: String, description: String) {
                        isLoading(false)
                        showErrorMessage(title, description, BaseActivity.ActionOnError.CLOSE)
                    }
                })
    }
}
