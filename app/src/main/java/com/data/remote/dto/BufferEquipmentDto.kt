package com.data.remote.dto

import com.google.gson.annotations.SerializedName

data class BufferEquipmentDto(
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
    @SerializedName("skill") val skill: Skill
)

data class Skill(
    @SerializedName("buff")  val buff: Buff
)

data class Buff(
    @SerializedName("skillInfo") val skillInfo: SkillInfo,
    @SerializedName("equipment") val equipment: List<Equipment>
)

data class SkillInfo(
    @SerializedName("skillId") val skillId: String,
    @SerializedName("name") val name: String,
    @SerializedName("option") val option: SkillOption
)

data class SkillOption(
    @SerializedName("level") val level: Int,
    @SerializedName("desc") val desc: String,
    @SerializedName("values") val values: List<String>
)

data class Equipment(
    @SerializedName("slotId") val slotId: String,
    @SerializedName("slotName") val slotName: String,
    @SerializedName("itemId") val itemId: String,
    @SerializedName("itemName") val itemName: String,
    @SerializedName("itemTypeId") val itemTypeId: String,
    @SerializedName("itemType") val itemType: String,
    @SerializedName("itemTypeDetailId") val itemTypeDetailId: String,
    @SerializedName("itemTypeDetail") val itemTypeDetail: String,
    @SerializedName("itemAvailableLevel") val itemAvailableLevel: Int,
    @SerializedName("itemRarity") val itemRarity: String,
    @SerializedName("setItemId") val setItemId: String?,
    @SerializedName("setItemName") val setItemName: String?,
    @SerializedName("reinforce") val reinforce: Int,
    @SerializedName("amplificationName") val amplificationName: String?,
    @SerializedName("refine") val refine: Int
)