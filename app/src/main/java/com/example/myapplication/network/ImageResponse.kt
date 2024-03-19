package com.example.myapplication.network
import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("rows")
    val servers: List<CharacterImg>
)