package com.example.myapplication.network
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NeopleApiClient {
    private val BASE_URL = "https://api.neople.co.kr/"
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
            return retrofit!!
        }
}

