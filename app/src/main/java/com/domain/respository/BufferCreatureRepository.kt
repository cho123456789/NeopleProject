package com.domain.respository

import com.data.remote.dto.Avatar
import com.data.remote.dto.Buff
import com.data.remote.dto.BufferAvaterDto
import com.data.remote.dto.BufferCreatureDto
import com.data.remote.dto.BufferEquipment
import com.data.remote.dto.BufferEquipmentDto
import com.data.remote.dto.CreatureDto
import com.data.remote.dto.EquipmentDto
import retrofit2.Response

interface BufferCreatureRepository {
    suspend fun getBufferCreature(
        serverId: String,
        characterId: String,
        apiKey: String
    ): Response<BufferCreatureDto>
}