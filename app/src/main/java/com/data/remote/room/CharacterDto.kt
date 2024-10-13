package com.data.remote.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
public data class CharacterDto (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val characterId: String,
    val characterServer : String,
    val characterName: String
)