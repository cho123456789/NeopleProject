package com.domain.use_case

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import com.common.Resource
import com.domain.respository.CharacterImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import java.io.IOException
import javax.inject.Inject

class GetCharacterImageUseCase @Inject constructor(
    private val repository: CharacterImageRepository
) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    operator fun invoke(
        serverId: String,
        characterId: String,
        zoom: Int
    ): Flow<Resource<ResponseBody>> =
        flow {
            emit(Resource.Loading())
            try {
                // Fetch the response from the repository
                val response = repository.getCharacterImage(serverId, characterId, zoom)
                // Log the raw JSON response
                Log.d("API Response", response.raw().toString())

                // Check if the response is successful
                if (response.isSuccessful) {
                    val info = response.body()
                    Log.d("Parsed Response", info.toString()) // 로그에 파싱된 데이터 출력
                    if (info != null) {
                        emit(Resource.Success(info))
                    } else {
                        emit(Resource.Error("No data available"))
                    }
                } else {
                    emit(Resource.Error("Error ${response.code()}: ${response.message()}"))
                }
            } catch (e: HttpException) {
                emit(Resource.Error("Connection error"))
            } catch (e: IOException) {
                emit(Resource.Error("Code error"))
            }
        }
}
