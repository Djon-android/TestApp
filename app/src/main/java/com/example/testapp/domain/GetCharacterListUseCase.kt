package com.example.testapp.domain

class GetCharacterListUseCase(private val repository: CharacterRepository) {

    operator fun invoke() = repository.getCharacterList()
}