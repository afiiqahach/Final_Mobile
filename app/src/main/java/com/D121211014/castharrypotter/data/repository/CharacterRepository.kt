package com.D121211014.castharrypotter.data.repository

import com.D121211014.castharrypotter.data.response.GetCharacterResponse
import com.D121211014.castharrypotter.data.source.remote.ApiService

class CharacterRepository (private val apiService: ApiService) {

    suspend fun getCharacter(): GetCharacterResponse {
        return apiService.getCharacter()
    }

}