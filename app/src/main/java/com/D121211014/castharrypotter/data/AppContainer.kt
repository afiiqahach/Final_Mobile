package com.D121211014.castharrypotter.data

import com.D121211014.castharrypotter.data.repository.CharacterRepository
import com.D121211014.castharrypotter.data.source.remote.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create

interface AppContainer {
    val characterRepository: CharacterRepository
}

class DefaultAppContainer: AppContainer {
    private val BASE_URL = "https://pixabay.com"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val characterRepository: CharacterRepository
        get() = CharacterRepository(retrofitService)
}