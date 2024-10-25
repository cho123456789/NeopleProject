package com.example.myapplication.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.common.Resource
import com.data.remote.dto.Avatar
import com.data.remote.dto.AvatarDto
import com.data.remote.dto.AvatarItem
import com.data.remote.dto.BufferAvaterDto
import com.data.remote.dto.BufferCreatureDto
import com.data.remote.dto.BufferEquipment
import com.data.remote.dto.CreatureDto
import com.data.remote.dto.Item
import com.data.remote.dto.TalismanDto
import com.data.remote.dto.TalismanWithRunes
import com.domain.use_case.GetAvatarUseCase
import com.domain.use_case.GetBufferAvatarUseCase
import com.domain.use_case.GetBufferCreatureUseCase
import com.domain.use_case.GetBufferEquipmentUseCase
import com.domain.use_case.GetCharacterEquipmentUseCase
import com.domain.use_case.GetTalismanUseCase
import com.presentation.CharacterListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TalismanViewModel @Inject constructor(
    private val getTalismanUseCase: GetTalismanUseCase
) : ViewModel() {

    private val _talismansItem = MutableStateFlow<List<TalismanWithRunes>>(emptyList())
    val talismansItem: StateFlow<List<TalismanWithRunes>> = _talismansItem

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getTalismans(serverId: String, characterId: String) {
        getTalismanUseCase(serverId, characterId).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    val characterResponse = resource.data
                    if (characterResponse != null) {
                        _talismansItem.value = characterResponse.talismans
                        Log.d("avatar",characterResponse.toString())
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
