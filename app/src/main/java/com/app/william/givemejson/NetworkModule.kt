package com.app.william.givemejson

import com.google.gson.Gson
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class NetworkModule {

   private val gson=Gson()

    private fun spec(): ConnectionSpec {
        return ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2)
                .allEnabledCipherSuites()
                .build()
    }

    private fun client(): OkHttpClient {
        return OkHttpClient.Builder()
                .connectionSpecs(Collections.singletonList(spec()))
                .build()

    }

    fun retrofit(): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client())
                .baseUrl("https://my-json-server.typicode.com/")
                .build()
    }
}