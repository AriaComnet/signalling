package com.hamidrezabashiri.signaling.data.data_source.remote

import com.hamidrezabashiri.signaling.data.model.Login
import com.hamidrezabashiri.signaling.data.model.LookUp
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @POST("lookup")
    @FormUrlEncoded
    suspend fun sendMessage(@FieldMap params: Map<String, String>): Response<LookUp>

    @POST("login")
    @FormUrlEncoded
    suspend fun login(@FieldMap params: Map<String, String>): Response<Login>

}