package com.data.remote
import com.data.remote.dto.CharacterInfoDto
import com.example.myapplication.network.CharacterResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NeopleApiImageService {

    @GET("df/servers/{serverId}/characters/{characterId}")
    suspend fun getCharacterImage(
        @Path("serverId") serverId: String,
        @Path("characterId") characterId: String,
        @Query("zoom") zoom: Int
    ): Response<ResponseBody>
}