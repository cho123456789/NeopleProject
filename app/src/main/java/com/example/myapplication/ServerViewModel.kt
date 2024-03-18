package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.network.NeopleApiService
import com.example.myapplication.network.ServerResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerViewModel(
     val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) :ViewModel() {
    private val API_KEY = "tqUtxGZX3aszriKokC1rUj4FWuR0ndi9"

    private val neopleApiService = Retrofit.Builder()
        .baseUrl("https://api.neople.co.kr/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NeopleApiService::class.java)

    private val _serverNames = MutableStateFlow<List<String>>(emptyList())
    val serverNames: StateFlow<List<String>> = _serverNames

    private val _serverIds = MutableStateFlow<List<String>>(emptyList())
    val serverId: StateFlow<List<String>> = _serverIds

    init{
        _serverIds.value = emptyList()
        _serverIds.value = emptyList()
    }
    fun getServers() {
        viewModelScope.launch {
            val serverResponse = getServersFromApi()
            if (serverResponse != null) {
                val servers = serverResponse.servers
                val serverId = servers.map{
                    it.serverId
                }
                val serverNames = servers.map {
                    it.serverName
                }
                _serverNames.value = serverNames
                _serverIds.value = serverId
            } else {
                // 서버 정보를 가져오지 못한 경우 처리
            }
        }
    }
    private suspend fun getServersFromApi(): ServerResponse? {
        return withContext(ioDispatcher) {
            try {
                val response = neopleApiService.getServers(API_KEY).execute()
                if (response.isSuccessful) {
                    response.body()
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        }
    }
}