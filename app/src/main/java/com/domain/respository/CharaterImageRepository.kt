package com.domain.respository

import com.data.remote.dto.CharacterInfoDto
import com.data.remote.dto.EquipmentDto
import com.example.myapplication.network.CharacterResponse
import okhttp3.ResponseBody
import retrofit2.Response

interface CharacterImageRepository {
    suspend fun getCharacterImage(
        serverId: String,
        characterId: String,
        zoom: Int
    ): Response<ResponseBody>

}