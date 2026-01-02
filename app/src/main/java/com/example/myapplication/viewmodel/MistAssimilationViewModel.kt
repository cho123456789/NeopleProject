package com.example.myapplication.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.common.Resource
import com.data.remote.dto.MistAssimilationDto
import com.domain.use_case.GetMistAssimilationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MistAssimilationViewModel @Inject constructor(
    private val getMistAssimilationUseCase: GetMistAssimilationUseCase
) : ViewModel() {

    private val _mistAssimilationInfo = MutableStateFlow<MistAssimilationDto?>(null)
    val mistAssimilationInfo: StateFlow<MistAssimilationDto?> = _mistAssimilationInfo

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getMistAssimilation(serverId: String, characterId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            _errorMessage.value = null

            getMistAssimilationUseCase(serverId, characterId).onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _isLoading.value = false
                        _mistAssimilationInfo.value = resource.data
                        Log.d("MistAssimilationViewModel", "Mist Assimilation loaded successfully")
                    }

                    is Resource.Error -> {
                        _isLoading.value = false
                        _errorMessage.value = resource.message ?: "안개융화 조회 중 오류가 발생했습니다."
                        Log.e("MistAssimilationViewModel", "Error: ${resource.message}")
                    }

                    is Resource.Loading -> {
                        _isLoading.value = true
                    }

                    else -> {}
                }
            }.launchIn(viewModelScope)
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }
}
