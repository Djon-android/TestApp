package com.example.testapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character ORDER BY idCharacter")
    fun getAllCharacter(): LiveData<List<CharacterDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacter(characterList: List<CharacterDbModel>)

    @Query("DELETE FROM character")
    suspend fun deleteAllCharacter()
}