package com.jp.app.ui.sample.view

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import com.jp.app.BR
import com.jp.app.R
import com.jp.app.common.view.BaseFragment
import com.jp.app.common.view.IBaseFragmentCallback
import com.jp.app.common.viewModel.BaseViewModel
import com.jp.app.databinding.SampleFragmentBinding
import com.jp.app.model.SampleView
import com.jp.app.ui.sample.adapter.SampleAdapter
import com.jp.app.ui.sample.viewModel.SampleViewModel
import kotlinx.android.synthetic.main.sample_fragment.*

class SampleFragment : BaseFragment<SampleFragmentBinding, SampleFragment.FragmentCallback>() {

    val LAYOUT_ID = R.layout.sample_fragment

    private var mSampleViewModel: SampleViewModel? = null
    private var mGridLayoutManager: LinearLayoutManager? = null
    private var mAdapter: SampleAdapter? = null

    companion object {
        fun newInstance(bundle: Bundle?) = SampleFragment().apply {
            arguments = bundle ?: Bundle()
        }
    }


    interface FragmentCallback : IBaseFragmentCallback {
        fun loadSampleInfo(Sample: SampleView)
    }

    override fun getViewModel(): BaseViewModel {
        mSampleViewModel = ViewModelProviders.of(this, mViewModelFactory).get(SampleViewModel::class.java)
        return mSampleViewModel as SampleViewModel
    }

    override fun getLayoutId(): Int {
        return LAYOUT_ID
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }


    override fun onViewLoaded(savedInstanceState: Bundle?, view: View?) {
        super.onViewLoaded(savedInstanceState, view)
        setUpRecyclerView()
        callGetSamples()
    }

    override fun subscribeToLiveData() {
        mSampleViewModel!!.getSampleViewSelected().observe(this, Observer { sampleView ->
            mCallback.loadSampleInfo(sampleView!!)
        })
    }

    private fun callGetSamples() {
        mSampleViewModel!!.callGetSamplesUseCase()
    }

    private fun setUpRecyclerView() {
        mGridLayoutManager = GridLayoutManager(activity, 3)
        recycler_view.layoutManager = mGridLayoutManager
        mAdapter = SampleAdapter(mSampleViewModel!!)
        recycler_view.adapter = mAdapter
    }


    @VisibleForTesting (otherwise = VisibleForTesting.NONE)
    fun getAdapter(): SampleAdapter? {
        return mAdapter
    }

}
