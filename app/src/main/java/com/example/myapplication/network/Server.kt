package com.example.myapplication.network

import com.google.gson.annotations.SerializedName

data class Servers (
    @SerializedName("serverId")
    var serverId : String,
    @SerializedName("serverName")
    var serverName : String
)