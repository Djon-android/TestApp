package com.example.testapp.domain

import androidx.lifecycle.LiveData

interface CharacterRepository {

    fun getCharacterList(): LiveData<List<Character>>

    fun loadData(page: Int)
}