package com.domain.respository

import com.data.remote.dto.StatusDto
import retrofit2.Response

interface StatusRepository {
    suspend fun getStatus(
        serverId: String,
        characterId: String,
        apiKey: String
    ): Response<StatusDto>
}
