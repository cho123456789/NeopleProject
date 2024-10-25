package com.data.remote.repository

import com.common.Constants
import com.data.remote.NeopleApiService
import com.data.remote.dto.Avatar
import com.data.remote.dto.AvatarDto
import com.data.remote.dto.Buff
import com.data.remote.dto.BufferAvaterDto
import com.data.remote.dto.BufferEquipment
import com.data.remote.dto.BufferEquipmentDto
import com.data.remote.dto.CharacterInfoDto
import com.data.remote.dto.TalismanDto
import com.domain.respository.AvatarRepository
import com.domain.respository.BufferAvatarRepository
import com.domain.respository.BufferEquipmentRepository
import com.domain.respository.CharacterInfoRepository
import com.domain.respository.CharacterSettingRepository
import com.domain.respository.TalismanRepository
import com.example.myapplication.network.CharacterResponse
import retrofit2.Response
import javax.inject.Inject

class TalismanRepositoryImpl @Inject constructor(
    private val api : NeopleApiService,
) : TalismanRepository {

    override suspend fun getTalisman(
        serverId: String,
        characterId: String,
        apiKey: String
    ): Response<TalismanDto> {
        return api.getTalisman(serverId,characterId,Constants.API_KEY)
    }
}
