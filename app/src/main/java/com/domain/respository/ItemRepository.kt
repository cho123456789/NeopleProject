package com.domain.respository

import com.data.remote.dto.ItemDto
import retrofit2.Response

interface ItemRepository {
    suspend fun getItemDetail(
        itemId: String,
        apiKey :String
    ): Response<ItemDto>
}