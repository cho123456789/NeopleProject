package com.example.myapplication

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.common.Resource
import com.data.remote.NeopleApiService
import com.data.remote.dto.CharacterResponse
import com.data.remote.dto.Item
import com.domain.model.characterItem
import com.domain.use_case.GetCharacterEquipmentUseCase
import com.domain.use_case.GetCharacterInfoUseCase
import com.domain.use_case.GetCharacterSettingUseCase
import com.presentation.CharacterListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CharacterEquipmentViewModel @Inject constructor(
    private val getCharacterEquipmentUseCase: GetCharacterEquipmentUseCase
) : ViewModel() {

    private val _characterId = MutableStateFlow<List<String?>>(emptyList())
    val characterId: StateFlow<List<String?>> = _characterId.asStateFlow()

    private val _equipment = MutableStateFlow<List<Item>>(emptyList())
    val equipment: StateFlow<List<Item>> = _equipment

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getCharacterEquipment(serverId: String, characterId: String) {
        getCharacterEquipmentUseCase(serverId, characterId).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    val characterResponse = resource.data
                    if (characterResponse != null) {
                        _equipment.value = characterResponse.equipment
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
