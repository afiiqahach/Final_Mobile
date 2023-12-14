package com.D121211014.castharrypotter.ui.activities.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.D121211014.castharrypotter.data.models.Character
import com.D121211014.castharrypotter.data.repository.CharacterRepository
import com.D121211014.castharrypotter.MyApplication
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MainUiState {
    data class Success(val character: List<Character>) :MainUiState
    object Error : MainUiState
    object Loading : MainUiState
}

class MainViewModel(private val characterRepository: CharacterRepository) : ViewModel() {
    var mainUiState: MainUiState by mutableStateOf(MainUiState.Loading)
        private set

    fun getCharacter() = viewModelScope.launch {
        mainUiState = MainUiState.Loading
        try {
            val result = characterRepository.getCharacter()
            mainUiState = MainUiState.Success(result.hits.orEmpty())
        } catch (e: IOException) {
            e.printStackTrace()
            mainUiState = MainUiState.Error
        }
    }

    init {
        getCharacter()
    }

    companion object{
        var Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication)
                val characterRepository = application.container.characterRepository
                MainViewModel(characterRepository)
            }
        }
    }
}