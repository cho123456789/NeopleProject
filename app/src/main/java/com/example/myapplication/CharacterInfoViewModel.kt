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
import com.domain.model.characterItem
import com.domain.use_case.GetCharacterInfoUseCase
import com.presentation.CharacterListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CharacterInfoViewModel @Inject constructor(
    private val getCharacterInfoUseCase: GetCharacterInfoUseCase,
) : ViewModel() {

    private val _character = MutableStateFlow<characterItem?>(null)
    val character: StateFlow<characterItem?> = _character

    private val _characterId = MutableStateFlow<List<String?>>(emptyList())
    val characterId: StateFlow<List<String?>> = _characterId.asStateFlow()

    private val _characterName =MutableStateFlow<List<String?>>(emptyList())
    val characterName: StateFlow<List<String?>> = _characterName

    private val _jobName = MutableStateFlow("")
    val jobName : StateFlow<String> = _jobName

    private val _serverId = MutableStateFlow("")
    val serverId: StateFlow<String> = _serverId

    private val _level = MutableStateFlow<Int>(0)
    val level: StateFlow<Int> = _level

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getCharacterInfo(serverId: String, characterNameItem: String) {
        getCharacterInfoUseCase(serverId, characterNameItem).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    val characterResponse = resource.data
                    val servers = characterResponse?.charactItem
                    val characterIds = servers?.map { it.characterId }
                    Log.d("viewmodel", characterIds.toString())
                    val characterName = servers?.map { it.characterName}
                    if(characterIds != null) {_characterId.value = characterIds}
                    if(characterName != null){ _characterName.value = characterName }
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
