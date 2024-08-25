package com.example.myapplication.network
import com.data.remote.dto.CharacterInfoDto
import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("rows")
    val charactItem: List<CharacterInfoDto>
)