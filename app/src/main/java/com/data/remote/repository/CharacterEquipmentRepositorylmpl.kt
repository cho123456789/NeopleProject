package com.data.remote.repository

import com.common.Constants
import com.common.Constants.API_KEY
import com.data.remote.NeopleApiService
import com.data.remote.dto.CharacterInfoDto
import com.data.remote.dto.EquipmentDto
import com.domain.respository.CharacterEquipmentRepository
import retrofit2.Response
import javax.inject.Inject

class CharacterEquipmentRepositorylmpl @Inject constructor(
    private val api : NeopleApiService,
) : CharacterEquipmentRepository {
    override suspend fun getEquipment(
        serverId: String,
        characterId: String,
        apiKey: String
    ): Response<EquipmentDto> {
        return api.getEquipment(serverId,characterId, API_KEY)
    }
}
