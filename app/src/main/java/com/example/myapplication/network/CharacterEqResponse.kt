package com.example.myapplication.network
import com.data.remote.dto.EquipmentDto
import com.google.gson.annotations.SerializedName

data class CharacterEqResponse(
    @SerializedName("equipment")
    val characterEqItem: List<EquipmentDto>
)