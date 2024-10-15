package com.example.myapplication.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.common.Resource
import com.data.remote.dto.Item
import com.domain.use_case.GetBufferEquipmentUseCase
import com.domain.use_case.GetCharacterEquipmentUseCase
import com.presentation.CharacterListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BufferEquipmentViewModel @Inject constructor(
    private val getBufferEquipmentUseCase: GetBufferEquipmentUseCase
) : ViewModel() {



    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getCharacterEquipment(serverId: String, characterId: String) {
        getBufferEquipmentUseCase(serverId, characterId).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    val characterResponse = resource.data
                    if (characterResponse != null) {
//                        _equipment.value = characterResponse.equipment
//                        Log.d("_equipment",_equipment.value.toString())
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
            }
        }.launchIn(viewModelScope)
    }
}
