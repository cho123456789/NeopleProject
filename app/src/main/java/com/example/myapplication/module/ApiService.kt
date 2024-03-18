package com.example.myapplication.module
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.network.NeopleApiClient
import com.example.myapplication.network.NeopleApiService
import com.example.myapplication.network.ServerResponse
import com.example.myapplication.network.Servers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiService : AppCompatActivity() {

    private val API_KEY = "tqUtxGZX3aszriKokC1rUj4FWuR0ndi9"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiService = NeopleApiClient.getClient()?.create(NeopleApiService::class.java)
        val call = apiService!!.getServers(API_KEY)
        call.enqueue(object : Callback<ServerResponse> {

            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(call: Call<ServerResponse>, response: Response<ServerResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val servers: List<Servers> = response.body()!!.servers
                        Log.d("ApiSerivce",servers.toString());
                        for(server in servers){
                            println("ServerId : ${server.serverId},Server Name :${server.serverName}")
                        }
                } else {
                    // Handle unsuccessful response
                }
            }

            override fun onFailure(call: Call<ServerResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }
}