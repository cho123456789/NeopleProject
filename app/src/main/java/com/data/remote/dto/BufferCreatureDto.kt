package com.data.remote.dto

import com.google.gson.annotations.SerializedName

data class BufferCreatureDto(
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
    @SerializedName("skill") val skill: CreatureSkill
)

data class CreatureSkill(
    @SerializedName("buff") val buff: CreatureBuff
)


data class CreatureBuff(
    @SerializedName("skillInfo") val skillInfo: CreatureSkillInfo,
    @SerializedName("creature") val creature: List<CreatureDto>
)


data class CreatureSkillInfo(
    @SerializedName("skillId") val skillId: String,
    @SerializedName("name") val name: String,
    @SerializedName("option") val option: CreatureOptionDto
)
data class CreatureOptionDto(
    @SerializedName("level") val level: Int,
    @SerializedName("desc") val desc: String,
    @SerializedName("values") val values: List<String>
)

data class CreatureDto(
    @SerializedName("itemId") val itemId: String,
    @SerializedName("itemName") val itemName: String,
    @SerializedName("itemRarity") val itemRarity: String,
    @SerializedName("enchant")  val creatureEnchant: EnchantDTO?
)

data class EnchantDTO(
    @SerializedName("reinforceSkill") val reinforceSkill: List<ReinforceSkillDto>? // Add this line
)
data class ReinforceSkillDto(
    @SerializedName("jobId") val jobId: String,
    @SerializedName("jobName") val jobName: String,
    @SerializedName("skills") val skills: List<SkillDetailDTO>
)
data class SkillDetailDTO(
    @SerializedName("skillId") val skillId: String,
    @SerializedName("name") val name: String?,
    @SerializedName("value") val value: String
)