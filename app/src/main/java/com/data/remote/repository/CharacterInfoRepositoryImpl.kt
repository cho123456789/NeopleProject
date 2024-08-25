package com.data.remote.repository

import com.common.Constants
import com.data.remote.NeopleApiService
import com.domain.respository.CharacterInfoRepository
import com.example.myapplication.network.CharacterResponse
import retrofit2.Response
import javax.inject.Inject

class CharacterInfoRepositoryImpl @Inject constructor(
    private val api: NeopleApiService,
) : CharacterInfoRepository {
    // Implementation here
    override suspend fun getCharacterInfo(
        serverId: String,
        characterName: String,
        apiKey: String
    ): Response<CharacterResponse> {
        return api.getCharacterInfo(serverId, characterName, Constants.API_KEY)
    }
}
