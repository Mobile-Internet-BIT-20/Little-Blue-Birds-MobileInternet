package com.lwy.bluebird.Service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.POST

interface bbWebService {

    companion object{
        var baseUrl = ""
        val service: bbWebService by lazy{
            val retrofit = Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(bbWebService::class.java)
        }
    }
}