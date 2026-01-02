package com.example.myapplication.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.common.Resource
import com.data.remote.dto.AvatarItem
import com.domain.use_case.GetAvatarUseCase
import com.presentation.CharacterListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AvatarViewModel @Inject constructor(
    private val getAvatarUseCase: GetAvatarUseCase
) : ViewModel() {

    private val _AvatarItem = MutableStateFlow<List<AvatarItem>>(emptyList())
    val AvatarItem: StateFlow<List<AvatarItem>> = _AvatarItem

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getAvatar(serverId: String, characterId: String) {
        getAvatarUseCase(serverId, characterId).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    val characterResponse = resource.data
                    if (characterResponse != null) {
                        _AvatarItem.value = characterResponse.avatar
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
