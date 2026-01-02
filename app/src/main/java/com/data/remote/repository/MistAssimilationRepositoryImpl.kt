package com.data.remote.repository

import com.common.Constants
import com.data.remote.NeopleApiService
import com.data.remote.dto.MistAssimilationDto
import com.domain.respository.MistAssimilationRepository
import retrofit2.Response
import javax.inject.Inject

class MistAssimilationRepositoryImpl @Inject constructor(
    private val api: NeopleApiService
) : MistAssimilationRepository {
    override suspend fun getMistAssimilation(
        serverId: String,
        characterId: String,
        apiKey: String
    ): Response<MistAssimilationDto> {
        return api.getMistAssimilation(serverId, characterId, Constants.API_KEY)
    }
}
