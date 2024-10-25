package com.data.remote.dto

import com.google.gson.annotations.SerializedName


data class AvatarDto(
    @SerializedName("serverId")val serverId: String,
    @SerializedName("characterId")val characterId: String,
    @SerializedName("characterName")val characterName: String,
    @SerializedName("level")val level: Int,
    @SerializedName("jobId")val jobId: String,
    @SerializedName("jobGrowId")val jobGrowId: String,
    @SerializedName("jobName")val jobName: String,
    @SerializedName("jobGrowName")val jobGrowName: String,
    @SerializedName("fame")val fame: Int,
    @SerializedName("adventureName")val adventureName: String,
    @SerializedName("guildId")val guildId: String,
    @SerializedName("guildName")val guildName: String,
    @SerializedName("avatar")val avatar: List<AvatarItem>
)

data class AvatarItem(
    @SerializedName("slotId")val slotId: String,
    @SerializedName("slotName")val slotName: String,
    @SerializedName("itemId")val itemId: String,
    @SerializedName("itemName")val itemName: String,
    @SerializedName("itemRarity")val itemRarity: String,
    @SerializedName("clone")val clone: AvatarClone?,
    @SerializedName("optionAbility")val optionAbility: String,
    @SerializedName("emblems") val emblems: List<AvatarEmblem>?
)

data class AvatarClone(
    @SerializedName("itemId")val itemId: String?,
    @SerializedName("itemName") val itemName: String?
)

data class AvatarEmblem(
    @SerializedName("slotNo")val slotNo: Int?,
    @SerializedName("slotColor")val slotColor: String?,
    @SerializedName("itemId")val itemId: String?,
    @SerializedName("itemName")val itemName: String?,
    @SerializedName("itemRarity")val itemRarity: String?
)

