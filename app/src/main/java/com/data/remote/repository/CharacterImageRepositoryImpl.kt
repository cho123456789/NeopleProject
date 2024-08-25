package com.data.remote.repository

import com.data.remote.NeopleApiImageService
import com.data.remote.NeopleApiService
import com.data.remote.dto.CharacterInfoDto
import com.domain.respository.CharacterImageRepository
import com.domain.respository.CharacterInfoRepository
import com.domain.respository.CharacterSettingRepository
import com.example.myapplication.network.CharacterResponse
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class CharacterImageRepositoryImpl @Inject constructor(
    private val api : NeopleApiImageService,
) : CharacterImageRepository {
    override suspend fun getCharacterImage(
        serverId: String,
        characterId: String,
        zoom: Int
    ): Response<ResponseBody> {
        return api.getCharacterImage(serverId,characterId, zoom)
    }
}
