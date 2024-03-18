package com.example.myapplication.network
import com.google.gson.annotations.SerializedName

data class ServerResponse(
    @SerializedName("rows")
    val servers: List<Servers>
)