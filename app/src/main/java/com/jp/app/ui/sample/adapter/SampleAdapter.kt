package com.jp.app.ui.sample.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jp.app.R
import com.jp.app.databinding.SampleComponentBinding
import com.jp.app.model.SampleView
import java.util.*

class SampleAdapter
constructor(callBack: SampleAdapterCallBack) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mList: MutableList<SampleView> = ArrayList()
    private var mListener: SampleAdapterCallBack = callBack

    interface SampleAdapterCallBack {
        fun sampleClicked(adapterPosition: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.sample_component, parent, false)
        return GameImageViewHolder(v)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        drawGame(holder as GameImageViewHolder,  mList[position])
    }

    private fun drawGame(holder: GameImageViewHolder, sample: SampleView) {
        holder.bind(sample)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun addSamples(newSamples: List<SampleView>) {
        mList.addAll(newSamples)
        notifyDataSetChanged()
    }

    fun clearItems() {
        mList.clear()
    }

    inner class GameImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var mSampleComponentBinding = SampleComponentBinding.bind(itemView)

        fun bind(sample: SampleView) {
            mSampleComponentBinding.sample = sample
            mSampleComponentBinding.gameClickListener = this
        }

        fun gamePressed() {
            mListener.sampleClicked(adapterPosition)
        }
    }

}
