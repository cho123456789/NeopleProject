package com.example.myapplication.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.common.Resource
import com.data.remote.dto.Avatar
import com.data.remote.dto.BufferAvaterDto
import com.data.remote.dto.BufferCreatureDto
import com.data.remote.dto.BufferEquipment
import com.data.remote.dto.CreatureDto
import com.data.remote.dto.Item
import com.domain.use_case.GetBufferAvatarUseCase
import com.domain.use_case.GetBufferCreatureUseCase
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
    private val getBufferEquipmentUseCase: GetBufferEquipmentUseCase,
    private val getBufferAvatarUseCase: GetBufferAvatarUseCase,
    private val getBufferCreatureUseCase: GetBufferCreatureUseCase
) : ViewModel() {

    private val _bufferEquipment = MutableStateFlow<List<BufferEquipment>>(emptyList())
    val bufferEquipment: StateFlow<List<BufferEquipment>> = _bufferEquipment

    private val _bufferAvatar = MutableStateFlow<List<Avatar>>(emptyList())
    val bufferAvatar: StateFlow<List<Avatar>> = _bufferAvatar

    private val _bufferCreature = MutableStateFlow<List<CreatureDto>>(emptyList())
    val bufferCreature: StateFlow<List<CreatureDto>> = _bufferCreature

    private val _bufferCreatureName = MutableStateFlow<List<String>>(emptyList())
    val bufferCreatureName: StateFlow<List<String>> = _bufferCreatureName

    private val _bufflevel = MutableStateFlow<Int>(0)
    val bufflevel: StateFlow<Int> = _bufflevel

    private val _buffname = MutableStateFlow<String>("")
    val buffname: StateFlow<String> = _buffname


    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getBuffCharacterEquipment(serverId: String, characterId: String) {
        getBufferEquipmentUseCase(serverId, characterId).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    val characterResponse = resource.data
                    if (characterResponse != null) {
                        _bufferEquipment.value = characterResponse.skill.buff.equipment

                        _bufflevel.value = characterResponse.skill.buff.skillInfo.option.level
                        _buffname.value = characterResponse.skill.buff.skillInfo.name

                        Log.d("_equipment",_bufferEquipment.value.toString())
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
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getBuffCharacterAvatar(serverId: String, characterId: String) {
        getBufferAvatarUseCase(serverId, characterId).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    val characterResponse = resource.data
                    if (characterResponse != null) {
                        _bufferAvatar.value = characterResponse.skill.buff.avatar
                        Log.d("_bufferAvataritem",_bufferAvatar.value.toString())
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
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getBuffCharacterCreature(serverId: String, characterId: String) {
        getBufferCreatureUseCase(serverId, characterId).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    val characterResponse = resource.data
                    if (characterResponse != null) {
                        _bufferCreature.value = characterResponse.skill.buff.creature
                        _bufferCreatureName.value = characterResponse.skill.buff.creature.map { it.itemName }
                        Log.d("_bufferCreatureitem",_bufferCreature.value.toString())
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
