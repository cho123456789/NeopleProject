package com.data.remote.dto

import com.google.gson.annotations.SerializedName


data class TalismanDto(
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
    @SerializedName("talismans") val talismans: List<TalismanWithRunes>
)

data class TalismanWithRunes(
    @SerializedName("talisman")val talisman: Talisman,
    @SerializedName("runes")val runes: List<Rune>
)
data class Talisman(
    @SerializedName("slotNo")val slotNo: Int,
    @SerializedName("itemId")val itemId: String,
    @SerializedName("itemName") val itemName: String
)

data class Rune(
    @SerializedName("slotNo")val slotNo: Int,
    @SerializedName("itemId")val itemId: String,
    @SerializedName("itemName")val itemName: String
)
