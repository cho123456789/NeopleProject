package com.domain.respository

import com.data.remote.dto.CharacterInfoDto
import com.data.remote.dto.EquipmentDto
import com.example.myapplication.network.CharacterResponse
import okhttp3.ResponseBody
import retrofit2.Response

interface CharacterInfoRepository {
    suspend fun getCharacterInfo(
        serverId: String,
        characterName: String,
        apiKey: String
    ): Response<CharacterResponse>

}