package com.domain.respository

import com.data.remote.dto.CharacterInfoDto
import com.data.remote.dto.EquipmentDto
import okhttp3.ResponseBody
import retrofit2.Response

interface CharacterRepository {
    suspend fun getCharacterImage(
        serverId: String,
        characterId: String,
        zoom: Int
    ): Response<ResponseBody>

    suspend fun getCharacterId(
        serverId: String,
        characterId: String,
        apiKey: String
    ): Response<CharacterInfoDto>

    suspend fun getEquipment(
        serverId: String,
        characterId: String,
        apiKey: String
    ): Response<EquipmentDto>

}