package com.data.remote.dto.di

import com.data.remote.NeoplelmageService
import com.data.remote.NeopleApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Install this module in the SingletonComponent
class AppModule {

    @Provides
    @Singleton
    fun provideNeopleApiService(): NeopleApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.neople.co.kr/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NeopleApiService::class.java)
    }

    @Provides
    fun provideNeopleApiServiceImg(): NeoplelmageService {
        return Retrofit.Builder()
            .baseUrl("https://img-api.neople.co.kr/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NeoplelmageService::class.java)
    }
}
