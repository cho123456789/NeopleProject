package com.domain.respository

import com.data.remote.dto.EquipmentDto
import okhttp3.ResponseBody
import retrofit2.Response

interface CharacterEquipmentRepository {
    suspend fun getEquipment(
        serverId: String,
        characterId: String,
        apiKey: String
    ): Response<EquipmentDto>
}