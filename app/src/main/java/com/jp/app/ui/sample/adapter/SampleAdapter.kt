package com.jp.app.ui.sample.adapter

import android.net.Uri
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jp.app.R
import com.jp.app.model.SampleView
import com.jp.app.utils.ImageHelper
import java.util.ArrayList

class SampleAdapter
    constructor(callBack: SampleAdapterCallBack): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mList: MutableList<SampleView> = ArrayList()

    private var mListener: SampleAdapterCallBack  = callBack

    interface SampleAdapterCallBack {
        fun sampleClicked(adapterPosition: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.sample_component, parent, false)
        return ItemViewHolder(v)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val sample = mList[position]
        drawSample(holder as ItemViewHolder, sample, position)
    }

    private fun drawSample(holder: ItemViewHolder, sample: SampleView, position: Int) {
        if (!sample.title.isNullOrBlank()) {
            holder.textView.text = sample.title
        }

        if (!sample.urlLogo.isNullOrBlank()) {
            holder.loadImage(sample.urlLogo!!)
        }

        holder.addOnClickListener(View.OnClickListener {
            if (mListener != null) {
                mListener!!.sampleClicked(position)
            }
        })
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun addSamples(newsamples: List<SampleView>) {
        mList.addAll(newsamples)
        notifyDataSetChanged()
    }

    fun clearItems() {
        mList.clear()
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var cardView: CardView = itemView.findViewById(R.id.card_view)

        var textView: AppCompatTextView = itemView.findViewById(R.id.sample_title)

        var imageView: AppCompatImageView = itemView.findViewById(R.id.sample_image)


        fun loadImage(imagePath: String) {
            val uri = Uri.parse(imagePath)
            ImageHelper.loadImage(itemView.context, uri, imageView)
        }

        fun addOnClickListener(listener: View.OnClickListener) {
            cardView.setOnClickListener(listener)
        }
    }

}