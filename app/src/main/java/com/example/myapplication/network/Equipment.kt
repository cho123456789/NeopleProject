package com.example.myapplication.network

import com.google.gson.annotations.SerializedName


data class CharacterEquipment(
    @SerializedName("characterId")
    val characterId: String,
    @SerializedName("characterName")
    val characterName: String,
    @SerializedName("level")
    val level: Int,
    @SerializedName("jobId")
    val jobId: String,
    @SerializedName("jobGrowId")
    val jobGrowId: String,
    @SerializedName("jobName")
    val jobName: String,
    @SerializedName("jobGrowName")
    val jobGrowName: String,
    @SerializedName("adventureName")
    val adventureName: String,
    @SerializedName("guildId")
    val guildId: String?,
    @SerializedName("guildName")
    val guildName: String?,
    @SerializedName("equipment")
    val equipment: List<Item>
)
data class Item(
    @SerializedName("slotId")
    val slotId: String,
    @SerializedName("slotName")
    val slotName: String,
    @SerializedName("itemId")
    val itemId: String,
    @SerializedName("itemName")
    val itemName: String,
    @SerializedName("itemTypeId")
    val itemTypeId: String,
    @SerializedName("itemType")
    val itemType: String,
    @SerializedName("itemTypeDetailId")
    val itemTypeDetailId: String,
    @SerializedName("itemTypeDetail")
    val itemTypeDetail: String,
    @SerializedName("itemAvailableLevel")
    val itemAvailableLevel: Int,
    @SerializedName("itemRarity")
    val itemRarity: String,
    @SerializedName("setItem")
    val setItem: SetItem?, // 이 부분은 주어진 JSON 데이터에 없는데 여기에 맞추어서 필드를 추가하거나 없애야 합니다.
    @SerializedName("reinforce")
    val reinforce: Int,
    @SerializedName("itemGradeName")
    val itemGradeName: String?,
    @SerializedName("transformInfo")
    val transformInfo: TransformInfo?, // 이 부분은 주어진 JSON 데이터에 없는데 여기에 맞추어서 필드를 추가하거나 없애야 합니다.
    @SerializedName("enchant")
    val enchant: Enchant?, // 이 부분은 주어진 JSON 데이터에 없는데 여기에 맞추어서 필드를 추가하거나 없애야 합니다.
    @SerializedName("amplificationName")
    val amplificationName: String?,
    @SerializedName("refine")
    val refine: Int,
    @SerializedName("sirocoInfo")
    val sirocoInfo: SirocoInfo?, // 이 부분은 주어진 JSON 데이터에 없는데 여기에 맞추어서 필드를 추가하거나 없애야 합니다.
    @SerializedName("upgradeInfo")
    val upgradeInfo: UpgradeInfo? //
)

data class SetItem(
    @SerializedName("setItemId")
    val setItemId: String?,
    @SerializedName("setItemName")
    val setItemName: String?
)

data class TransformInfo(
    @SerializedName("explain")
    val explain: String,
    @SerializedName("explainDetail")
    val explainDetail: String,
    @SerializedName("optionType")
    val optionType: String,
    @SerializedName("active")
    val active: Boolean
)

data class Enchant(
    @SerializedName("explain")
    val explain: String,
    @SerializedName("status")
    val status: List<Status>
)

data class Status(
    @SerializedName("name")
    val name: String,
    @SerializedName("value")
    val value: Any // JSON 데이터에서는 숫자 혹은 문자열로 표현될 수 있습니다.
)

data class SirocoInfo(
    @SerializedName("options")
    val options: List<SirocoOption>
)

data class SirocoOption(
    @SerializedName("explain")
    val explain: String,
    @SerializedName("explainDetail")
    val explainDetail: String,
    @SerializedName("buffExplain")
    val buffExplain: String,
    @SerializedName("buffExplainDetail")
    val buffExplainDetail: String
)

data class UpgradeInfo(
    @SerializedName("itemId")
    val itemId: String,
    @SerializedName("itemName")
    val itemName: String
)