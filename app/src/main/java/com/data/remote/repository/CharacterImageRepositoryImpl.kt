package com.data.remote.repository

import com.data.remote.NeoplelmageService
import com.domain.respository.CharacterImageRepository
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class CharacterImageRepositoryImpl @Inject constructor(
    private val api : NeoplelmageService,
) : CharacterImageRepository {
    override suspend fun getCharacterImage(
        serverId: String,
        characterId: String,
        zoom: Int
    ): Response<ResponseBody> {
        return api.getCharacterImage(serverId,characterId, zoom)
    }
}
