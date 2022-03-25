package com.example.testapp.domain

class LoadDataUseCase(private val repository: CharacterRepository) {

    operator fun invoke(page: Int) = repository.loadData(page)
}