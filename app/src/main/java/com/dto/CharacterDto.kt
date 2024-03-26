package com.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_table")
data class CharacterDto (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val characterName: String,
    val characterId: String
)