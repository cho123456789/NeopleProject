package com.data.remote.repository

import com.common.Constants
import com.data.remote.NeopleApiService
import com.data.remote.dto.ItemDto
import com.domain.respository.ItemRepository
import retrofit2.Response
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val api : NeopleApiService,
) : ItemRepository {

    override suspend fun getItemDetail(
        itemId: String,
        apiKey: String
    ): Response<ItemDto> {
        return api.getItemDetail(itemId,apiKey)
    }
}
