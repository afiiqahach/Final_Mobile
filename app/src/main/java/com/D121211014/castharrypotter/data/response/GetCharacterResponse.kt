package com.D121211014.castharrypotter.data.response

import com.D121211014.castharrypotter.data.models.Character
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GetCharacterResponse(
    @SerialName("hits")
    val hits: List<Character>,
    @SerialName("total")
    val total: Int,
    @SerialName("totalHits")
    val totalHits: Int
)