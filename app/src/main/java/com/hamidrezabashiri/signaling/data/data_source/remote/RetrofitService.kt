package com.hamidrezabashiri.signaling.data.data_source.remote

import com.hamidrezabashiri.signaling.data.model.LookUp
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitService {

    @POST("lookup")
    @FormUrlEncoded
    suspend fun sendMessage(@FieldMap params: Map<String, String>): LookUp

    @POST("login")
    @FormUrlEncoded
    suspend fun login(@FieldMap params: Map<String, String>): LookUp

    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("http://aiotel.ir:443/api/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}