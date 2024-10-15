package com.data.remote.repository

import com.common.Constants
import com.data.remote.NeopleApiService
import com.data.remote.dto.BufferEquipmentDto
import com.data.remote.dto.CharacterInfoDto
import com.domain.respository.BufferEquipmentRepository
import com.domain.respository.CharacterInfoRepository
import com.domain.respository.CharacterSettingRepository
import com.example.myapplication.network.CharacterResponse
import retrofit2.Response
import javax.inject.Inject

class BufferEquipmentRepositoryImpl @Inject constructor(
    private val api : NeopleApiService,
) : BufferEquipmentRepository {

    override suspend fun getBufferEquipment(
        serverId: String,
        characterId: String,
        apiKey: String
    ): Response<BufferEquipmentDto> {
       return api.getBufferEquipment(serverId,characterId,Constants.API_KEY)
    }
}
