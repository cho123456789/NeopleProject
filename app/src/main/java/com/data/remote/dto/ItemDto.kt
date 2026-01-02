package com.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ItemDto (
    @SerializedName("itemId") val itemId: String,
    @SerializedName("itemName") val itemName: String,
    @SerializedName("itemRarity") val itemRarity: String,
    @SerializedName("itemTypeId") val itemTypeId: String,
    @SerializedName("itemType") val itemType: String,
    @SerializedName("itemTypeDetailId") val itemTypeDetailId: String,
    @SerializedName("itemTypeDetail") val itemTypeDetail: String,
    @SerializedName("itemAvailableLevel") val itemAvailableLevel: Int,
    @SerializedName("itemExplain") val itemExplain: String,
    @SerializedName("itemExplainDetail") val itemExplainDetail: String,
    @SerializedName("itemFlavorText") val itemFlavorText: String,
    @SerializedName("fame") val fame: Int,
    @SerializedName("setItemId") val setItemId: String? = null,
    @SerializedName("setItemName") val setItemName: String? = null,
    @SerializedName("jobs") val jobs: List<Job>,
    @SerializedName("itemStatus") val itemStatus: List<ItemStatus>,
    @SerializedName("talismanInfo") val talismanInfo: TalismanInfo? = null,
    @SerializedName("obtainInfo") val obtainInfo: ObtainInfo
)

data class Job(
    @SerializedName("jobId") val jobId: String,
    @SerializedName("jobName") val jobName: String
)
data class ItemStatus(
    @SerializedName("name") val name: String,
    @SerializedName("value") val value: Int
)
data class TalismanInfo(
    @SerializedName("skillId") val skillId: String,
    @SerializedName("skillName") val skillName: String,
    @SerializedName("explain") val explain: String,
    @SerializedName("explainDetail") val explainDetail: String
)
data class ObtainInfo(
    @SerializedName("dungeon") val dungeon: List<Dungeon>,
    @SerializedName("shop") val shop: List<Shop>
)
data class Dungeon(
    @SerializedName("type") val type: String,
    @SerializedName("rows") val rows: List<DungeonRow>
)
data class DungeonRow(
    @SerializedName("name") val name: String
)
data class Shop(
    @SerializedName("rows") val rows: List<ShopRow>
)
data class ShopRow(
    @SerializedName("name") val name: String,
    @SerializedName("details") val details: List<String>
)