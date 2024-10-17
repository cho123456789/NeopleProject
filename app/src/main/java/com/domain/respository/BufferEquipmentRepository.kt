package com.domain.respository

import com.data.remote.dto.Buff
import com.data.remote.dto.BufferEquipment
import com.data.remote.dto.BufferEquipmentDto
import com.data.remote.dto.EquipmentDto
import retrofit2.Response

interface BufferEquipmentRepository {
    suspend fun getBufferEquipment(
        serverId: String,
        characterId: String,
        apiKey: String
    ): Response<BufferEquipmentDto>
}