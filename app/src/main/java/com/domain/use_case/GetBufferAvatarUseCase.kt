package com.domain.use_case

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import com.common.Constants.API_KEY
import com.common.Resource
import com.data.remote.dto.Avatar
import com.data.remote.dto.Buff
import com.data.remote.dto.BufferAvaterDto
import com.data.remote.dto.BufferEquipment
import com.data.remote.dto.BufferEquipmentDto
import com.data.remote.dto.CharacterInfoDto
import com.data.remote.dto.EquipmentDto
import com.domain.respository.BufferAvatarRepository
import com.domain.respository.BufferEquipmentRepository
import com.domain.respository.CharacterEquipmentRepository
import com.domain.respository.CharacterSettingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetBufferAvatarUseCase @Inject constructor(
    private val repository: BufferAvatarRepository
) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    operator fun invoke(serverId: String, characterId: String): Flow<Resource<BufferAvaterDto>> =
        flow {
            emit(Resource.Loading())
            try {
                // Fetch the response from the repository
                val response = repository.getBufferAvatar(serverId, characterId, API_KEY)
                // Log the raw JSON response
                Log.d("API Response", response.raw().toString())

                // Check if the response is successful
                if (response.isSuccessful) {
                    val info = response.body()
                    //Log.d("Parsed Response", info.toString()) // 로그에 파싱된 데이터 출력
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
