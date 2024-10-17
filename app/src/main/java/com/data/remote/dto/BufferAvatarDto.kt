package com.data.remote.dto

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
    val skill: Skill
)


data class AvatarSkill(
    val buff: Buff
)


data class AvatarBuff(
    val skillInfo: SkillInfo,
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
    val slotId: String,
    val slotName: String,
    val itemId: String,
    val itemName: String,
    val itemRarity: String,
    val clone: Clone,
    val optionAbility: String,
    val emblems: List<Emblem>
)

data class Clone(
    val itemId: String?,
    val itemName: String?
)

data class Emblem(
    val slotNo: Int,
    val slotColor: String,
    val itemId: String,
    val itemName: String,
    val itemRarity: String
)