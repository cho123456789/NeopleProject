package com.example.myapplication.network
import com.google.gson.annotations.SerializedName

data class CharacterEqResponse(
    @SerializedName("equipment")
    val characterEqItem: List<CharacterEquipment>
)