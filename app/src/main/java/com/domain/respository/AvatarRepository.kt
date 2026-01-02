package com.domain.respository

import com.data.remote.dto.AvatarDto
import retrofit2.Response

interface AvatarRepository {
    suspend fun getAvatar(
        serverId: String,
        characterId: String,
        apiKey: String
    ): Response<AvatarDto>
}