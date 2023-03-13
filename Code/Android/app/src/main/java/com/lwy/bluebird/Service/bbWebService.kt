package com.lwy.bluebird.Service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.lwy.bluebird.Data.responseModel
import com.lwy.bluebird.Data.userLogin
import com.lwy.bluebird.Data.userRegister
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface bbWebService {

    @POST("api/login")
    fun login(@Body userLogin: userLogin): Call<responseModel>

    @POST("api/register")
    fun register(@Body userRegister: userRegister): Call<responseModel>

    companion object{
        val client = OkHttpClient.Builder().build()
        //val gson = GsonBuilder().setLenient().create()
        var baseUrl = "http://39.105.205.166:8080/"
        val service: bbWebService by lazy{
            val retrofit = Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            retrofit.create(bbWebService::class.java)
        }
    }
}
