package com.hamidrezabashiri.signaling.data.repository

import com.hamidrezabashiri.signaling.data.model.Login
import com.hamidrezabashiri.signaling.data.model.LookUp
import retrofit2.Response

interface AuthenticationRepository {

    suspend fun lookUp(params: Map<String, String>): Response<LookUp>

    suspend fun login(params: Map<String, String>): Response<Login>
}