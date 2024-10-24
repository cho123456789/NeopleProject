package com.data.remote.repository

import com.common.Constants
import com.data.remote.NeopleApiService
import com.data.remote.dto.Avatar
import com.data.remote.dto.Buff
import com.data.remote.dto.BufferAvaterDto
import com.data.remote.dto.BufferCreatureDto
import com.data.remote.dto.BufferEquipment
import com.data.remote.dto.BufferEquipmentDto
import com.data.remote.dto.CharacterInfoDto
import com.domain.respository.BufferAvatarRepository
import com.domain.respository.BufferCreatureRepository
import com.domain.respository.BufferEquipmentRepository
import com.domain.respository.CharacterInfoRepository
import com.domain.respository.CharacterSettingRepository
import com.example.myapplication.network.CharacterResponse
import retrofit2.Response
import javax.inject.Inject

class BufferCreatureRepositoryImpl @Inject constructor(
    private val api : NeopleApiService,
) : BufferCreatureRepository {
    override suspend fun getBufferCreature(
        serverId: String,
        characterId: String,
        apiKey: String
    ): Response<BufferCreatureDto> {
        return api.getBufferCreature(serverId,characterId,Constants.API_KEY)
    }
}
