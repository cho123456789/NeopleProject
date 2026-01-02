package com.domain.use_case

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import com.common.Constants.API_KEY
import com.common.Resource
import com.data.remote.dto.MistAssimilationDto
import com.domain.respository.MistAssimilationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetMistAssimilationUseCase @Inject constructor(
    private val repository: MistAssimilationRepository
) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    operator fun invoke(
        serverId: String,
        characterId: String
    ): Flow<Resource<MistAssimilationDto>> =
        flow {
            emit(Resource.Loading())
            try {
                Log.d(
                    "GetMistAssimilationUseCase",
                    "Requesting mist assimilation - serverId: $serverId, characterId: $characterId"
                )

                val response = repository.getMistAssimilation(serverId, characterId, API_KEY)

                if (response.isSuccessful) {
                    val mistInfo = response.body()
                    Log.d("GetMistAssimilationUseCase", "Success - Data: $mistInfo")

                    if (mistInfo != null) {
                        emit(Resource.Success(mistInfo))
                    } else {
                        emit(Resource.Error("No mist assimilation data available"))
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMsg = "Error ${response.code()}: ${response.message()}"
                    Log.e("GetMistAssimilationUseCase", "$errorMsg - Body: $errorBody")
                    emit(Resource.Error(errorMsg))
                }
            } catch (e: HttpException) {
                Log.e("GetMistAssimilationUseCase", "HttpException", e)
                emit(Resource.Error("Connection error"))
            } catch (e: IOException) {
                Log.e("GetMistAssimilationUseCase", "IOException", e)
                emit(Resource.Error("Network error"))
            }
        }
}
