package com.example.myapplication.network

import com.data.remote.dto.BufferEquipment
import com.data.remote.dto.EquipmentDto
import com.google.gson.annotations.SerializedName

data class BufferResponse(
    @SerializedName("equipment")
    val BufferEqItem: List<BufferEquipment>
)
