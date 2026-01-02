package com.data.remote.dto

import com.google.gson.annotations.SerializedName

data class StatusDto(
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
    @SerializedName("buff") val buff: List<BuffInfo>?,
    @SerializedName("status") val status: List<StatusInfo>
)

data class BuffInfo(
    @SerializedName("name") val name: String,
    @SerializedName("level") val level: Int?,
    @SerializedName("status") val status: List<StatusInfo>
)

data class StatusInfo(
    @SerializedName("name") val name: String,
    @SerializedName("value") val value: Any  // Int 또는 Double 가능
)
