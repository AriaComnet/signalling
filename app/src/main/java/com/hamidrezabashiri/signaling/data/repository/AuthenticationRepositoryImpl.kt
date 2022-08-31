package com.hamidrezabashiri.signaling.data.repository

import com.hamidrezabashiri.signaling.data.data_source.remote.RetrofitService
import com.hamidrezabashiri.signaling.data.model.LookUp

class AuthenticationRepositoryImpl constructor(private val retrofitService: RetrofitService) :
    AuthenticationRepository {

    override suspend fun lookUp(params: Map<String, String>): LookUp {
        return retrofitService.sendMessage(params)
    }

    override suspend fun login(params: Map<String, String>) {
        retrofitService.login(params)
    }
}