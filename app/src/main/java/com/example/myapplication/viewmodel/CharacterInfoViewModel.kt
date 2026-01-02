package com.example.myapplication.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.common.Resource
import com.data.remote.dto.Item
import com.data.remote.room.AppDatabase
import com.data.remote.room.CharacterDao
import com.data.remote.room.CharacterDto
import com.domain.model.characterDto
import com.domain.use_case.GetCharacterEquipmentUseCase
import com.domain.use_case.GetCharacterImageUseCase
import com.domain.use_case.GetCharacterInfoUseCase
import com.domain.use_case.GetCharacterSettingUseCase
import com.example.myapplication.ui.Screen.saveCharacterId
import com.presentation.CharacterListState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
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
    private val getCharacterImageUseCase: GetCharacterImageUseCase,
    private val getCharacterSettingUseCase: GetCharacterSettingUseCase,
    private val characterDao: CharacterDao,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _character = MutableStateFlow<characterDto?>(null)
    val character: StateFlow<characterDto?> = _character

    private val _characterId = MutableStateFlow<List<String?>>(emptyList())
    val characterId: StateFlow<List<String?>> = _characterId.asStateFlow()

    private val _characterName = MutableStateFlow<List<String?>>(emptyList())
    val characterName: StateFlow<List<String?>> = _characterName

    private val _serverId = MutableStateFlow("")
    val serverId: StateFlow<String> = _serverId

    private val _ImageCheck = MutableStateFlow(false)
    val ImageCheck: StateFlow<Boolean> = _ImageCheck

    private val _characters = mutableStateOf<List<CharacterDto>>(emptyList())
    val characters: State<List<CharacterDto>> = _characters

    private val _imageBitmap = MutableStateFlow<ImageBitmap?>(null)
    val imageBitmap: StateFlow<ImageBitmap?> = _imageBitmap

    private val _jobGrowName = MutableStateFlow<String>("")
    val jobGrowName: StateFlow<String> = _jobGrowName

    private val _guildName = MutableStateFlow<String>("")
    val guildName: StateFlow<String> = _guildName

    private val _adventureName = MutableStateFlow<String>("")
    val adventureName: StateFlow<String> = _adventureName

    private val _equipment = MutableStateFlow<List<Item>>(emptyList())
    val equipment: StateFlow<List<Item>> = _equipment

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

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
        if (serverId.isEmpty()) {
            _errorMessage.value = "서버를 선택해주세요."
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            _errorMessage.value = null
            _isLoading.value = true
            getCharacterInfoUseCase(serverId, characterNameItem).onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _isLoading.value = false
                        val characterResponse = resource.data
                        val servers = characterResponse?.charactItem
                        val characterIds = servers?.map { it.characterId }
                        Log.d("characterIds", characterIds.toString())
                        val characterName = servers?.map { it.characterName }
                        if (characterIds != null && characterName != null) {
                            _characterId.value = characterIds
                            _characterName.value = characterName

                            Log.d("tag", _characterId.value.toString())
                            val characterIdJoined = characterIds.joinToString(", ")
                            val characterNameJoined = characterName.joinToString(", ")

                            getCharacterImage(serverId, characterIdJoined)
                            getCharacterSetting(serverId, characterIdJoined)
                            //getBuffEquipment(serverId,characterIds.joinToString(", "))
                            saveCharacter(
                                context = context,
                                characterId = characterIdJoined,
                                serverId = serverId
                            )

                            // 자동으로 캐릭터를 데이터베이스에 저장
                            viewModelScope.launch {
                                // 중복 체크: 같은 characterId가 이미 있는지 확인
                                val existingCharacters = characterDao.getAllCharacters()
                                val isAlreadyExists = existingCharacters.any {
                                    it.characterId == characterIdJoined && it.characterServer == serverId
                                }

                                if (!isAlreadyExists) {
                                    addCharacter(
                                        characterId = characterIdJoined,
                                        inputServerId = serverId,
                                        characterNameIds = characterNameJoined
                                    )
                                }
                            }
                        } else {
                            setErrorMessage("캐릭터를 찾을 수 없습니다.")
                        }
                    }

                    is Resource.Error -> {
                        _isLoading.value = false
                        setErrorMessage(resource.message ?: "캐릭터 조회 중 오류가 발생했습니다.")
                        Log.e("CharacterInfoViewModel", "Error: ${resource.message}")
                    }

                    is Resource.Loading -> {
                        _isLoading.value = true
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

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getCharacterImage(serverId: String, characterId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getCharacterImageUseCase(serverId, characterId, 3).onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        resource.data?.byteStream()?.use { inputStream ->
                            // Bitmap을 생성하고, ImageBitmap으로 변환
                            val bitmap = BitmapFactory.decodeStream(inputStream)
                            val imageBitmap = bitmap?.asImageBitmap()

                            // UI에서 사용할 수 있도록 MutableStateFlow 업데이트
                            _imageBitmap.value = imageBitmap
                            _ImageCheck.value = true
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

    fun resetImageCheck() {
        _ImageCheck.value = false
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getCharacterSetting(serverId: String, characterId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getCharacterSettingUseCase(serverId, characterId).onEach { resource ->
                when (resource) {
                    is Resource.Success -> {
                        val characterResponse = resource.data
//                        _characterName.value = characterResponse?.characterName.toString()
                        _jobGrowName.value = characterResponse?.jobGrowName.toString()
                        _guildName.value = characterResponse?.guildName.toString()
                        _adventureName.value = characterResponse?.adventureName.toString()

                        Log.d("_jobGrowName", jobGrowName.value)
                        Log.d("_guildName", guildName.value)
                        Log.d("_adventureName", adventureName.value)

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

    fun clearErrorMessage() {
        _errorMessage.value = null
    }

    fun setErrorMessage(message: String) {
        _errorMessage.value = message
    }

}

fun saveCharacter(context: Context, characterId: String, serverId: String) {
    // Call the use case or repository to save the character ID
    saveCharacterId(context, characterId, serverId)
}
fun saveCharacterId(context: Context, characterId: String , serverId: String) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString("characterId", characterId)
    editor.putString("serverId",serverId)
    editor.apply()
}
