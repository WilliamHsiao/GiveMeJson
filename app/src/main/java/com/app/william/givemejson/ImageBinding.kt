package com.app.william.givemejson

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.android.flexbox.FlexboxLayoutManager

object ImageBinding {
    @BindingAdapter("src")
    @JvmStatic
    fun setSrc(view: ImageView, bitmap: Bitmap? ) {
        bitmap?.let {
            view.setImageBitmap(bitmap)

            val lp = (view.parent as View).layoutParams
            if (lp is FlexboxLayoutManager.LayoutParams) {
                lp.flexGrow = 1f
            }
        }
    }
}