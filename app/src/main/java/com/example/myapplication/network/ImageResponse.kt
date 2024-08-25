package com.example.myapplication.network
import com.data.remote.dto.CharacterImgDto
import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("rows")
    val servers: List<CharacterImgDto>
)