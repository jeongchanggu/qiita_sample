package com.appsbygu.qiita.services

import com.appsbygu.qiita.interfaces.IApiService
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://qiita.com/"

class ApiService private constructor() {

    private object Holder { val INSTANCE = ApiService() }

    companion object {
        val instance: ApiService by lazy { Holder.INSTANCE }
    }

    private val rxAdapter: RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())

    fun getService(): IApiService {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build()
                .create(IApiService::class.java)
    }

}