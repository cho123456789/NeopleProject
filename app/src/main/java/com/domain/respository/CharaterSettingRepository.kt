package com.domain.respository

import com.data.remote.dto.CharacterInfoDto
import com.data.remote.dto.EquipmentDto
import com.example.myapplication.network.CharacterResponse
import okhttp3.ResponseBody
import retrofit2.Response

interface CharacterSettingRepository {
    suspend fun getCharacterSetting(
        serverId: String,
        characterId: String,
        apiKey: String
    ): Response<CharacterInfoDto>

}