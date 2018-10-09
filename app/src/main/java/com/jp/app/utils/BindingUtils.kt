package com.jp.app.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
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
        val requestBuilder = Glide.with(imageView.context)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
        requestBuilder.into(imageView)
    }
}
