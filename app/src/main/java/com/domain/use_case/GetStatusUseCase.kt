package com.domain.use_case

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import com.common.Constants.API_KEY
import com.common.Resource
import com.data.remote.dto.StatusDto
import com.domain.respository.StatusRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetStatusUseCase @Inject constructor(
    private val repository: StatusRepository
) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    operator fun invoke(serverId: String, characterId: String): Flow<Resource<StatusDto>> =
        flow {
            emit(Resource.Loading())
            try {
                Log.d(
                    "GetStatusUseCase",
                    "Requesting status - serverId: $serverId, characterId: $characterId"
                )

                val response = repository.getStatus(serverId, characterId, API_KEY)

                if (response.isSuccessful) {
                    val statusInfo = response.body()
                    Log.d("GetStatusUseCase", "Success - Data: $statusInfo")

                    if (statusInfo != null) {
                        emit(Resource.Success(statusInfo))
                    } else {
                        emit(Resource.Error("No status data available"))
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMsg = "Error ${response.code()}: ${response.message()}"
                    Log.e("GetStatusUseCase", "$errorMsg - Body: $errorBody")
                    emit(Resource.Error(errorMsg))
                }
            } catch (e: HttpException) {
                Log.e("GetStatusUseCase", "HttpException", e)
                emit(Resource.Error("Connection error"))
            } catch (e: IOException) {
                Log.e("GetStatusUseCase", "IOException", e)
                emit(Resource.Error("Network error"))
            }
        }
}
