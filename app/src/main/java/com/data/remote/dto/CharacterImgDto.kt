package com.data.remote.dto

import com.domain.model.ImageDto
import com.domain.model.characterItem
import com.google.gson.annotations.SerializedName

data class CharacterImgDto (
    @SerializedName("serverId")
    var serverId : String,
    @SerializedName("characterId")
    var characterId : String,
    @SerializedName("zoom")
    var zoome : String,
)
fun CharacterImgDto.toImgDto() : ImageDto{
    return ImageDto(
        serverId =serverId,
        characterId = characterId,
        zoome = zoome
    )
}