package com.example.myapplication.network
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NeopleApiService {
    @GET("df/servers")
    fun getServers(@Query("apikey") apiKey: String): Call<ServerResponse>
}