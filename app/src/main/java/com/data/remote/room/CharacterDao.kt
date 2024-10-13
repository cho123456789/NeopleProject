package com.data.remote.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterDao {
    @Query("SELECT * FROM characters")
    suspend fun getAllCharacters(): List<CharacterDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE) // 중복 시 데이터를 교체
    suspend fun insertCharacter(characterdto: CharacterDto)

    @Query("DELETE FROM characters WHERE id = :id")
    suspend fun deleteCharacterById(id: Long)

}