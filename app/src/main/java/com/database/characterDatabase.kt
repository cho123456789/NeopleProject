package com.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dto.CharacterDto

@Database(entities = [CharacterDto::class],version= 1, exportSchema = false)
abstract class characterDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: characterDatabase? = null

        fun getDatabase(context: Context): characterDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    characterDatabase::class.java,
                    "character_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}