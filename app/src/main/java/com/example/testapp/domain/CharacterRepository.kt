package com.example.testapp.domain

import androidx.lifecycle.LiveData

interface CharacterRepository {

    fun getCharacterList(): LiveData<List<Character>>

    suspend fun loadData(page: Int)
}