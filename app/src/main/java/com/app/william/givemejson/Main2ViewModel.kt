package com.app.william.givemejson

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Main2ViewModel : ViewModel() {
    private val networkModule: NetworkModule = NetworkModule()
    private val apiService: ApiService = networkModule.retrofit().create(ApiService::class.java)
    val json = MutableLiveData<List<PicData>>()
    var disposable: Disposable? = null


    init {
        apiService.getPicData().subscribeOn(Schedulers.io()).subscribe(object :
            SingleObserver<List<PicData>> {
            override fun onSuccess(t: List<PicData>) {
                json.postValue(t)
            }

            override fun onSubscribe(d: Disposable) {
                disposable = d
            }

            override fun onError(e: Throwable) {
                Log.e("Main2ViewModel","getPicData",e)
            }

        })
    }


    override fun onCleared() {
        disposable?.dispose()
    }
}