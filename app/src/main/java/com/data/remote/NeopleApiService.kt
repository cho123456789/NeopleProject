package com.data.remote
import com.data.remote.dto.CharacterInfoDto
import com.data.remote.dto.EquipmentDto
import com.example.myapplication.network.CharacterResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NeopleApiService {

    @GET("df/servers/{serverId}/characters/{characterId}")
    suspend fun getCharacterImage(
        @Path("serverId") serverId: String,
        @Path("characterId") characterId: String,
        @Query("zoom") zoom: Int
    ): Response<ResponseBody>

    @GET("df/servers/{serverId}/characters")
    suspend fun getCharacterInfo(
        @Path("serverId") serverId: String,
        @Query("characterName") characterName: String,
        @Query("apikey") apiKey: String,
    ): Response<CharacterResponse>

    @GET("df/servers/{serverId}/characters/{characterId}")
    suspend fun getCharacterSetting(
        @Path("serverId") serverId: String,
        @Path("characterId") characterId: String,
        @Query("apikey") apiKey: String
    ): Response<CharacterInfoDto>
//
//    @GET("df/servers/{serverId}/characters/{characterId}/equip/equipment")
//    suspend fun getEquipment(
//        @Path("serverId") serverId: String,
//        @Path("characterId") characterId: String,
//        @Query("apikey") apiKey: String,
//    ): Response<EquipmentDto>
}