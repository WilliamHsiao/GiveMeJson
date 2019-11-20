package com.app.william.givemejson

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.android.flexbox.FlexboxLayoutManager
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.InputStream
import java.net.URL

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