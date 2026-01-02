package com.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MistAssimilationDto(
    @SerializedName("serverId") val serverId: String,
    @SerializedName("characterId") val characterId: String,
    @SerializedName("characterName") val characterName: String,
    @SerializedName("level") val level: Int,
    @SerializedName("jobId") val jobId: String,
    @SerializedName("jobGrowId") val jobGrowId: String,
    @SerializedName("jobName") val jobName: String,
    @SerializedName("jobGrowName") val jobGrowName: String,
    @SerializedName("fame") val fame: Int,
    @SerializedName("adventureName") val adventureName: String,
    @SerializedName("guildId") val guildId: String?,
    @SerializedName("guildName") val guildName: String?,
    @SerializedName("mistAssimilation") val mistAssimilation: MistAssimilationInfo?
)

data class MistAssimilationInfo(
    @SerializedName("level") val level: Int,
    @SerializedName("expRate") val expRate: String,
    @SerializedName("status") val status: List<MistStatus>
)

data class MistStatus(
    @SerializedName("name") val name: String,
    @SerializedName("value") val value: Any  // String(%) 또는 Int 가능
)
