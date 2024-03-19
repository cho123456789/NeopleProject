package com.example.myapplication.network
import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("rows")
    val charactItem: List<Character>
)