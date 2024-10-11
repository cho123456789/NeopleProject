package com.data.remote.room.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Characters")
data class CharacterDto (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val characterId: String,
    val characterServer : String,
    val characterName: String,
)