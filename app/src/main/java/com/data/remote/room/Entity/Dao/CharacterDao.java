package com.data.remote.room.Entity.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.data.remote.room.Entity.CharacterDto;
import com.domain.model.characterDto;

import java.util.List;

@Dao
interface CharacterDao {

    // suspend fun 으로 실행 해야되는데 에러 발생 왜인지 잘모르겟음
    // 생성
//    @Insert
//    suspend fun insert(characterDto: CharacterDto)
//
//    // 조회
//    @Query("SELECT * FROM characters")
//    suspend fun getAllCharacters(): List<CharacterDto>
}