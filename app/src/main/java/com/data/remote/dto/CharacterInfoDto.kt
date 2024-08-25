package com.data.remote.dto

import com.domain.model.characterItem
import com.google.gson.annotations.SerializedName

data class CharacterInfoDto (
    @SerializedName("serverId") val serverId: String?,
    @SerializedName("characterId") val characterId: String?,
    @SerializedName("characterName") val characterName: String?,
    @SerializedName("level") val level: Int,
    @SerializedName("jobId") val jobId: String?,
    @SerializedName("jobGrowId") val jobGrowId: String?,
    @SerializedName("jobName") val jobName: String?,
    @SerializedName("jobGrowName") val jobGrowName: String?,
    @SerializedName("fame") val fame: Int,
    @SerializedName("adventureName") val adventureName: String?,
    @SerializedName("reinforce") val reinforce: Int,
    @SerializedName("guildName") val guildName: String?
)
data class CharacterResponse(
    @SerializedName("rows") val charactItem: List<CharacterInfoDto>
)