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
    private var disposable: Disposable? = null
    private val single = Single.create(SingleOnSubscribe<Bitmap> { emitter ->
        try {
            val img = URL(author).content as InputStream
            val bitmap = BitmapFactory.decodeStream(img)
            emitter.onSuccess(bitmap)
        } catch (e: Exception) {
            emitter.onError(e)
        }
    })

    fun download() {

        if (disposable == null) {

            single.subscribeOn(Schedulers.io())
                .subscribe(object : SingleObserver<Bitmap> {
                    override fun onSuccess(t: Bitmap) {
                        bitmap = t
                    }

                    override fun onSubscribe(d: Disposable) {
                        disposable = d
                    }

                    override fun onError(e: Throwable) {
                        Log.e("PicData", "download", e)
                    }


                })
        }
    }

    var bitmap: Bitmap?
        @Bindable get() = _bitmap
        set(value) {

            _bitmap = value
            notifyPropertyChanged(BR.bitmap)
        }
}
