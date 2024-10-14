package com.example.myapplication.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.common.Resource
import com.data.remote.room.AppDatabase
import com.data.remote.room.CharacterDao
import com.data.remote.room.CharacterDto
import com.domain.model.characterDto
import com.domain.use_case.GetCharacterInfoUseCase
import com.presentation.CharacterListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterInfoViewModel @Inject constructor(
    private val getCharacterInfoUseCase: GetCharacterInfoUseCase,
    private val characterDao: CharacterDao,
    private val appDatabase: AppDatabase
    // private val characterDao: CharacterDao
) : ViewModel() {

    private val _character = MutableStateFlow<characterDto?>(null)
    val character: StateFlow<characterDto?> = _character

    private val _characterId = MutableStateFlow<List<String?>>(emptyList())
    val characterId: StateFlow<List<String?>> = _characterId.asStateFlow()

    private val _characterName = MutableStateFlow<List<String?>>(emptyList())
    val characterName: StateFlow<List<String?>> = _characterName

    private val _jobName = MutableStateFlow("")
    val jobName: StateFlow<String> = _jobName

    private val _serverId = MutableStateFlow("")
    val serverId: StateFlow<String> = _serverId

    private val _level = MutableStateFlow<Int>(0)
    val level: StateFlow<Int> = _level

    private val _characters = mutableStateOf<List<CharacterDto>>(emptyList())
    val characters: State<List<CharacterDto>> = _characters

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            _characters.value = characterDao.getAllCharacters()
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getCharacterInfo(serverId: String, characterNameItem: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getCharacterInfoUseCase(serverId, characterNameItem).onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        val characterResponse = resource.data
                        val servers = characterResponse?.charactItem
                        val characterIds = servers?.map { it.characterId }
                        Log.d("characterIds", characterIds.toString())
                        val characterName = servers?.map { it.characterName }
                        if (characterIds != null) {
                            _characterId.value = characterIds
                        }
                        if (characterName != null) {
                            _characterName.value = characterName
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

    fun addCharacter(characterId: String, inputServerId: String, characterNameIds: String) {
        viewModelScope.launch {
            val characterDto = CharacterDto(
                characterId = characterId,
                characterServer = inputServerId,
                characterName = characterNameIds
            )
            characterDao.insertCharacter(characterDto)
            loadCharacters()
        }
    }

    fun deleteCharacter(characterId: Long) {
        viewModelScope.launch {
            characterDao.deleteCharacterById(characterId)
            loadCharacters()
        }
    }
}
