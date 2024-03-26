package com.example.myapplication.network
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NeopleApiService {
    @GET("df/servers")
    fun getServers(@Query("apikey") apiKey: String): Call<ServerResponse>
    @GET("df/servers/{serverId}/characters/{characterId}")
    suspend fun getCharacterImage(
        @Path("serverId") serverId: String,
        @Path("characterId") characterId: String,
        @Query("zoom") zoom: String
    ): Response<ResponseBody>

    @GET("df/servers/{serverId}/characters")
    fun getCharacterId(
        @Path("serverId") serverId : String,
        @Query("characterName") characterName : String,
        @Query("apikey") apiKey: String,
    ):Call<CharacterResponse>

    @GET("df/servers/{serverId}/characters/{characterId}")
    fun getCharacter(
        @Path("serverId") serverId : String,
        @Path("characterId") characterId : String,
        @Query("apikey") apiKey: String,
    ):Call<Character>

    @GET("df/servers/{serverId}/characters/{characterId}/equip/equipment")
    fun getEquipment(
        @Path("serverId") serverId : String,
        @Path("characterId") characterId : String,
        @Query("apikey") apiKey: String,
    ):Call<CharacterEquipment>
}