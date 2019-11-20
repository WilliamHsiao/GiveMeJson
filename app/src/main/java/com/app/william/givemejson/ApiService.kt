package com.app.william.givemejson

import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("/jerryexcc/MyJsonPlaceHolderDemo/posts")
    fun getPicData(): Single<List<PicData>>
}