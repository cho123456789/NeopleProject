package com.example.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dto.CharacterDto

interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characterDto: CharacterDto)

    @Query("SELECT * FROM character_table")
    fun getCharacterById(): LiveData<CharacterDto>

}