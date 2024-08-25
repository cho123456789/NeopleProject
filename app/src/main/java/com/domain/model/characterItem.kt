package com.domain.model

import com.google.gson.annotations.SerializedName

data class characterItem(
    val serverId: String? = null,
    val characterId: String,
    val characterName: String,
    val level: Int,
    val jobId: String,
    val jobGrowId: String,
    val jobName: String,
    val jobGrowName: String,
    val fame: Int
)
