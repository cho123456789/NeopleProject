package com.example.myapplication.network

import com.google.gson.annotations.SerializedName

data class CharacterImg (
    @SerializedName("serverId")
    var serverId : String,
    @SerializedName("characterId")
    var characterId : String,
    @SerializedName("zoom")
    var zoome : String,
)