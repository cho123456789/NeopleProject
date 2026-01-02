package com.data.remote.repository

import com.common.Constants
import com.data.remote.NeopleApiService
import com.data.remote.dto.StatusDto
import com.domain.respository.StatusRepository
import retrofit2.Response
import javax.inject.Inject

class StatusRepositoryImpl @Inject constructor(
    private val api: NeopleApiService
) : StatusRepository {
    override suspend fun getStatus(
        serverId: String,
        characterId: String,
        apiKey: String
    ): Response<StatusDto> {
        return api.getStatus(serverId, characterId, Constants.API_KEY)
    }
}
