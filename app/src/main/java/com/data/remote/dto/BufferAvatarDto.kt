package com.data.remote.dto

import com.google.gson.annotations.SerializedName

data class BufferAvaterDto(
    val serverId: String,
    val characterId: String,
    val characterName: String,
    val level: Int,
    val jobId: String,
    val jobGrowId: String,
    val jobName: String,
    val jobGrowName: String,
    val fame: Int,
    val adventureName: String,
    val guildId: String,
    val guildName: String,
    val skill: AvatarSkill,
)

data class AvatarSkill(
    val buff: AvatarBuff
)


data class AvatarBuff(
    val skillInfo: AvatarSkillInfo,
    val avatar: List<Avatar>
)


data class AvatarSkillInfo(
    val skillId: String,
    val name: String,
    val option: Option
)


data class Option(
    val level: Int,
    val desc: String,
    val values: List<String>
)

data class Avatar(
    @SerializedName("slotId") val slotId: String,
    @SerializedName("slotName") val slotName: String,
    @SerializedName("itemId") val itemId: String,
    @SerializedName("itemName") val itemName: String,
    @SerializedName("itemRarity") val itemRarity: String,
    @SerializedName("clone") val clone: Clone,
    @SerializedName("optionAbility") val optionAbility: String,
    @SerializedName("emblems") val emblems: List<Emblem>
)

data class Clone(
    @SerializedName("itemId")val itemId: String?,
    @SerializedName("itemName")val itemName: String?
)

data class Emblem(
    @SerializedName("slotNo")val slotNo: Int,
    @SerializedName("slotColor")val slotColor: String,
    @SerializedName("itemId")val itemId: String,
    @SerializedName("itemName")val itemName: String,
    @SerializedName("itemRarity")val itemRarity: String
)