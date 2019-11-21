package com.app.william.givemejson

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.SingleOnSubscribe
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.InputStream
import java.net.URL

data class PicData(
    val author: String? = null,
    val id: Int? = null,
    val title: String = "",
    private var _bitmap: Bitmap? = null
) : BaseObservable() {
    val single: Single<PicData> = Single.create { emitter ->
        try {
            val img = URL(author).content as InputStream
            this@PicData._bitmap= BitmapFactory.decodeStream(img)
            emitter.onSuccess(this@PicData)
        } catch (e: Exception) {
            emitter.onError(e)
        }
    }

    var bitmap: Bitmap?
        @Bindable get() = _bitmap
        set(value) {
            _bitmap = value
            notifyPropertyChanged(BR.bitmap)
        }
}
