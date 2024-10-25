package com.data.remote
import com.data.remote.dto.Avatar
import com.data.remote.dto.AvatarDto
import com.data.remote.dto.Buff
import com.data.remote.dto.BufferAvaterDto
import com.data.remote.dto.BufferCreatureDto
import com.data.remote.dto.BufferEquipment
import com.data.remote.dto.BufferEquipmentDto
import com.data.remote.dto.CharacterInfoDto
import com.data.remote.dto.EquipmentDto
import com.data.remote.dto.TalismanDto
import com.example.myapplication.network.CharacterResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NeopleApiService {

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

    @GET("df/servers/{serverId}/characters/{characterId}/equip/equipment")
    suspend fun getEquipment(
        @Path("serverId") serverId: String,
        @Path("characterId") characterId: String,
        @Query("apikey") apiKey: String,
    ): Response<EquipmentDto>

    @GET("df/servers/{serverId}/characters/{characterId}/skill/buff/equip/equipment")
    suspend fun getBufferEquipment(
        @Path("serverId") serverId: String,
        @Path("characterId") characterId: String,
        @Query("apikey") apiKey: String
    ):Response<BufferEquipmentDto>

    @GET("df/servers/{serverId}/characters/{characterId}/skill/buff/equip/avatar")
    suspend fun getBufferAvatar(
        @Path("serverId") serverId: String,
        @Path("characterId") characterId: String,
        @Query("apikey") apiKey: String
    ):Response<BufferAvaterDto>

    @GET("df/servers/{serverId}/characters/{characterId}/skill/buff/equip/creature")
    suspend fun getBufferCreature(
        @Path("serverId") serverId: String,
        @Path("characterId") characterId: String,
        @Query("apikey") apiKey: String
    ):Response<BufferCreatureDto>

    @GET("df/servers/{serverId}/characters/{characterId}/equip/avatar")
    suspend fun getAvatar(
        @Path("serverId") serverId: String,
        @Path("characterId") characterId: String,
        @Query("apikey") apiKey: String
    ):Response<AvatarDto>

    @GET("df/servers/{serverId}/characters/{characterId}/equip/talisman")
    suspend fun getTalisman(
        @Path("serverId") serverId: String,
        @Path("characterId") characterId: String,
        @Query("apikey") apiKey: String
    ):Response<TalismanDto>
}