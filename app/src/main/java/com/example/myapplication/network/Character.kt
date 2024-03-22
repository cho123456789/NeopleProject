package com.example.myapplication.network

import com.google.gson.annotations.SerializedName

data class Character (
    @SerializedName("serverId")
    var serverId : String,
    @SerializedName("characterId")
    var characterId : String,
    @SerializedName("characterName")
    var characterName : String,
    @SerializedName("level")
    var level : Int,
    @SerializedName("jobId")
    var jobId : String,
    @SerializedName("jobGrowId")
    var jobGrowId : String,
    @SerializedName("jobName")
    var jobName : String,
    @SerializedName("jobGrowName")
    var jobGrowName : String,
    @SerializedName("fame")
    var fame : Int,
    @SerializedName("adventureName")
    var adventureName : String,
)