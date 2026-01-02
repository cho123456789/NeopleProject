package com.data.remote.repository

import com.common.Constants
import com.data.remote.NeopleApiService
import com.domain.respository.CharacterInfoRepository
import com.example.myapplication.network.CharacterResponse
import retrofit2.Response
import java.net.URLEncoder
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
        // 한글 캐릭터 이름을 UTF-8로 인코딩
        val encodedCharacterName = URLEncoder.encode(characterName, "UTF-8")
        return api.getCharacterInfo(serverId, encodedCharacterName, Constants.API_KEY)
    }
}
