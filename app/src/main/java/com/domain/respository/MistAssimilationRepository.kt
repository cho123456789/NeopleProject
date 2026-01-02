package com.domain.respository

import com.data.remote.dto.MistAssimilationDto
import retrofit2.Response

interface MistAssimilationRepository {
    suspend fun getMistAssimilation(
        serverId: String,
        characterId: String,
        apiKey: String
    ): Response<MistAssimilationDto>
}
