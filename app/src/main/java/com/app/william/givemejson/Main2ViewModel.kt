package com.app.william.givemejson

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Main2ViewModel : ViewModel() {
    private val networkModule: NetworkModule = NetworkModule()
    private val apiService: ApiService = networkModule.retrofit().create(ApiService::class.java)
    val pic = MutableLiveData<PicData>()
    var disposable: Disposable? = null


    init {
        apiService.getPicData()
            .flatMapObservable { Observable.fromIterable(it) }
            .flatMapSingle { it.single }
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<PicData> {
                override fun onNext(t: PicData) {
                    pic.postValue(t)
                }

                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onError(e: Throwable) {
                    Log.e("Main2ViewModel", "getPicData", e)
                }

                override fun onComplete() {

                }

            })
    }


    override fun onCleared() {
        disposable?.dispose()
    }
}