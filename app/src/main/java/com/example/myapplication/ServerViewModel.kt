package com.example.myapplication

import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.network.Character
import com.example.myapplication.network.CharacterResponse
import com.example.myapplication.network.NeopleApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStream

class ServerViewModel(
    val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) :ViewModel() {
    private val API_KEY = "tqUtxGZX3aszriKokC1rUj4FWuR0ndi9"

    private val neopleApiService = Retrofit.Builder()
        .baseUrl("https://api.neople.co.kr/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NeopleApiService::class.java)

    private val neopleApiServiceImg = Retrofit.Builder()
        .baseUrl("https://img-api.neople.co.kr/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NeopleApiService::class.java)


    private val _characterId = MutableStateFlow<List<String>>(emptyList())
    val characterId: StateFlow<List<String>> = _characterId

    private val _jobGrowName = MutableStateFlow("")
    val jobGrowName : StateFlow<String> = _jobGrowName

    private val _adventureName = MutableStateFlow("")
    val adventureName : StateFlow<String> = _adventureName

    private val _characterName = MutableStateFlow("")
    val characterName: StateFlow<String> = _characterName

    private val _level = MutableStateFlow<List<Int>>(emptyList())
    val level: StateFlow<List<Int>> = _level



    private val _imageBitmap = MutableStateFlow<ImageBitmap?>(null)
    val imageBitmap: StateFlow<ImageBitmap?> = _imageBitmap


    init {
        _characterId.value = emptyList()
        _characterName.value = ""
        _level.value = emptyList()
        _jobGrowName.value = ""

    }

    fun getCharacterId(serverId: String, characterNameItem: String) {
        viewModelScope.launch {
            val characterResponse = getCharacterIdFromApi(serverId, characterNameItem)
            if (characterResponse != null) {
                val servers = characterResponse.charactItem
                val characterId = servers.map { it.characterId }
                _characterId.value = characterId
            } else {

            }
        }
    }
    fun getCharacter(serverId: String, characterNameItem: String) {
        viewModelScope.launch {
            val characterResponse = getCharacterFromApi(serverId, characterNameItem)
            if (characterResponse != null) {
                _characterName.value = characterResponse.characterName
                _jobGrowName.value = characterResponse.jobGrowName
                _adventureName.value = characterResponse.adventureName
            } else {

            }
        }
    }
    private suspend fun getCharacterFromApi(
        serverId: String,
        characterId: String
    ): Character? {
        return withContext(ioDispatcher) {
            try {
                val response = neopleApiService.getCharacter(serverId, characterId, API_KEY).execute()
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

    private suspend fun getCharacterIdFromApi(
        serverId: String,
        characterName: String
    ): CharacterResponse? {
        return withContext(ioDispatcher) {
            try {
                val response = neopleApiService.getCharacterId(serverId, characterName, API_KEY).execute()
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
    suspend fun getCharacterImageFromApi(serverId: String, characterId: String, zoom: String): Response<ResponseBody>? {
        return withContext(Dispatchers.IO) {
            try {
                neopleApiServiceImg.getCharacterImage(serverId, characterId, zoom)
            } catch (e: Exception) {
                Log.e("ServerViewModel", "Failed to fetch character image: ${e.message}")
                null
            }
        }
    }

    fun getCharacterImg(serverId: String, characterId: String, zoom: String) {
        viewModelScope.launch {
            try {
                val characterResponse = getCharacterImageFromApi(serverId, characterId, zoom)
                if (characterResponse?.isSuccessful == true) {
                    val inputStream: InputStream? = characterResponse.body()?.byteStream()
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    _imageBitmap.value = bitmap?.asImageBitmap()
                } else {
                    Log.e("ServerViewModel", "Failed to fetch character image: ${characterResponse?.message()}")
                }
            } catch (e: Exception) {
                Log.e("ServerViewModel", "Exception while fetching character image: ${e.message}")
            }
        }
    }
}
