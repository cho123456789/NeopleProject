package com.example.myapplication.viewmodel

import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.common.Resource
import com.domain.use_case.GetCharacterImageUseCase
import com.presentation.CharacterListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CharacterImageViewModel @Inject constructor(
    private val getCharacterImageUseCase: GetCharacterImageUseCase
) : ViewModel() {

    private val _imageBitmap = MutableStateFlow<ImageBitmap?>(null)
    val imageBitmap: StateFlow<ImageBitmap?> = _imageBitmap

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getCharacterImage(serverId: String, characterId: String) {
        getCharacterImageUseCase(serverId, characterId,3).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.byteStream()?.use { inputStream ->
                        // Bitmap을 생성하고, ImageBitmap으로 변환
                        val bitmap = BitmapFactory.decodeStream(inputStream)
                        val imageBitmap = bitmap?.asImageBitmap()

                        // UI에서 사용할 수 있도록 MutableStateFlow 업데이트
                        _imageBitmap.value = imageBitmap
                    }
                }
                is Resource.Error -> {
                    CharacterListState(
                        error = resource.message ?: "An unexpected error occurred"
                    ).toString()
                }

                is Resource.Loading -> {
                    CharacterListState(
                        isLoading = resource.message ?: "data Loading..."
                    ).toString()
                }
                else -> {}
            }
        }.launchIn(viewModelScope)
    }
}
