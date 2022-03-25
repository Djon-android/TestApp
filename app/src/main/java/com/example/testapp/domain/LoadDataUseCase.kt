package com.example.testapp.domain

class LoadDataUseCase(private val repository: CharacterRepository) {

    suspend operator fun invoke(page: Int) = repository.loadData(page)
}