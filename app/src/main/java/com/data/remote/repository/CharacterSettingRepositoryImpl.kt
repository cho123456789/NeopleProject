package com.data.remote.repository

import com.common.Constants
import com.data.remote.NeopleApiService
import com.data.remote.dto.CharacterInfoDto
import com.domain.respository.CharacterInfoRepository
import com.domain.respository.CharacterSettingRepository
import com.example.myapplication.network.CharacterResponse
import retrofit2.Response
import javax.inject.Inject

class CharacterSettingRepositoryImpl @Inject constructor(
    private val api : NeopleApiService,
) : CharacterSettingRepository {
    // Implementation here
    override suspend fun getCharacterSetting(
        serverId: String,
        characterId: String,
        apiKey: String
    ): Response<CharacterInfoDto> {
        return api.getCharacterSetting(serverId,characterId, Constants.API_KEY)
    }
}
