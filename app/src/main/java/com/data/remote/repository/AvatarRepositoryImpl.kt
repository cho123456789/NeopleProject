package com.data.remote.repository

import com.common.Constants
import com.data.remote.NeopleApiService
import com.data.remote.dto.AvatarDto
import com.domain.respository.AvatarRepository
import retrofit2.Response
import javax.inject.Inject

class AvatarRepositoryImpl @Inject constructor(
    private val api : NeopleApiService,
) : AvatarRepository {

    override suspend fun getAvatar(
        serverId: String,
        characterId: String,
        apiKey: String
    ): Response<AvatarDto> {
       return api.getAvatar(serverId,characterId,Constants.API_KEY)
    }
}
