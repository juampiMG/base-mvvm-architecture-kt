package com.jp.app.utils

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.jp.app.model.SampleView
import com.jp.app.ui.sample.adapter.SampleAdapter

object BindingUtils {

    @JvmStatic
    @BindingAdapter("sample_adapter")
    fun addSamples(recyclerView: RecyclerView, sampleViews: List<SampleView>) {
        val adapter = recyclerView.adapter as SampleAdapter?
        if (adapter != null) {
            adapter.clearItems()
            adapter.addSamples(sampleViews)
        }

    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImageUrl(imageView: ImageView, url: String) {
        val context = imageView.context
        Glide.with(context).load(url).into(imageView)
    }
}