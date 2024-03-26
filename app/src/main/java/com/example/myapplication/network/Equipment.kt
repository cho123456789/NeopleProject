package com.example.myapplication.network

import com.google.gson.annotations.SerializedName

data class CharacterEquipment(
    @SerializedName("characterId")
    val characterId: String,
    @SerializedName("characterName")
    val characterName: String,
    val level: Int,
    val jobId: String,
    val jobGrowId: String,
    val jobName: String,
    val jobGrowName: String,
    val adventureName: String,
    val guildId: String?,
    val guildName: String?,
    val equipment: List<Equipment>
)

data class Equipment(
    val slotId: String,
    val slotName: String,
    val item: Item
)

data class Item(
    val itemId: String,
    val itemName: String,
    val itemTypeId: String,
    val itemType: String,
    val itemTypeDetailId: String,
    val itemTypeDetail: String,
    val itemAvailableLevel: Int,
    val itemRarity: String,
    val setItem: SetItem?,
    val reinforce: Int,
    val itemGradeName: String?,
    val transformInfo: TransformInfo?,
    val enchant: Enchant?,
    val amplificationName: String?,
    val refine: Int,
    val sirocoInfo: SirocoInfo?,
    val upgradeInfo: UpgradeInfo?
)

data class SetItem(
    val setItemId: String?,
    val setItemName: String?
)

data class TransformInfo(
    val explain: String,
    val explainDetail: String,
    val optionType: String,
    val active: Boolean
)

data class Enchant(
    val status: List<Status>
)

data class Status(
    val name: String,
    val value: Any // JSON 데이터에서는 숫자 혹은 문자열로 표현될 수 있습니다.
)

data class SirocoInfo(
    val options: List<SirocoOption>
)

data class SirocoOption(
    val explain: String,
    val explainDetail: String,
    val buffExplain: String,
    val buffExplainDetail: String
)

data class UpgradeInfo(
    val itemId: String,
    val itemName: String
)