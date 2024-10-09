package com.example.myapplication.viewmodel

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.common.Resource
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
class CharacterSettingViewModel @Inject constructor(
    private val getCharacterSettingUseCase: GetCharacterSettingUseCase
) : ViewModel() {

    private val _characterId = MutableStateFlow<List<String?>>(emptyList())
    val characterId: StateFlow<List<String?>> = _characterId.asStateFlow()

    private val _jobGrowName =MutableStateFlow<String>("")
    val jobGrowName: StateFlow<String> = _jobGrowName

    private val _guildName =MutableStateFlow<String>("")
    val guildName: StateFlow<String> = _guildName

    private val _characterName =MutableStateFlow<String>("")
    val characterName: StateFlow<String> = _characterName

    private val _adventureName =MutableStateFlow<String>("")
    val adventureName: StateFlow<String> = _adventureName

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getCharacterSetting(serverId: String, characterId: String) {
        getCharacterSettingUseCase(serverId, characterId).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    val characterResponse = resource.data
                    _characterName.value = characterResponse?.characterName.toString()
                    _jobGrowName.value = characterResponse?.jobGrowName.toString()
                    _guildName.value = characterResponse?.guildName.toString()
                    _adventureName.value = characterResponse?.adventureName.toString()
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
