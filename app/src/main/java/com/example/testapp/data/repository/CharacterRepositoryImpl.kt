package com.example.testapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.testapp.data.database.AppDatabase
import com.example.testapp.data.mapper.CharacterMapper
import com.example.testapp.data.network.ApiFactory
import com.example.testapp.domain.Character
import com.example.testapp.domain.CharacterRepository

class CharacterRepositoryImpl(
    private val application: Application
): CharacterRepository {

    private val characterDao = AppDatabase.getInstance(application).characterDao()
    private val apiService = ApiFactory.apiService
    private val mapper = CharacterMapper()

    override fun getCharacterList(): LiveData<List<Character>> {
        return Transformations.map(characterDao.getAllCharacter()) {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override suspend fun loadData(page: Int) {
        val containerDto = apiService.getListCharacter(page)
        val characterList = containerDto.characterDto
        characterList?.let {
            characterDao.insertAllCharacter(characterList.map { mapper.mapDtoToDbModel(it) })
        }
    }
}