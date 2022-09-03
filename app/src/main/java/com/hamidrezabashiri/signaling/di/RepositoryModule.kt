package com.hamidrezabashiri.signaling.di

import com.hamidrezabashiri.signaling.data.data_source.remote.ApiService
import com.hamidrezabashiri.signaling.data.repository.AuthenticationRepository
import com.hamidrezabashiri.signaling.data.repository.AuthenticationRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    /**
     * Provides RemoteDataRepository for access api service method
     */
    @Singleton
    @Provides
    fun provideAuthenticationRepository(
        apiService: ApiService,
    ): AuthenticationRepository {
        return AuthenticationRepositoryImpl(
            apiService
        )
    }

}