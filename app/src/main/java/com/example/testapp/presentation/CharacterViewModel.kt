package com.example.testapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.testapp.data.repository.CharacterRepositoryImpl
import com.example.testapp.domain.GetCharacterListUseCase
import com.example.testapp.domain.LoadDataUseCase
import kotlinx.coroutines.launch

class CharacterViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CharacterRepositoryImpl(application)

    private val getCharacterListUseCase = GetCharacterListUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val characterList = getCharacterListUseCase()

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun loadData(page: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            loadDataUseCase(page)
            _isLoading.value = false
        }
    }

    init {
        loadData(START_PAGE_DOWNLOAD)
    }

    companion object {
        private const val START_PAGE_DOWNLOAD = 1
    }
}