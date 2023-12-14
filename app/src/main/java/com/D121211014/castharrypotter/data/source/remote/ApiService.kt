package com.D121211014.castharrypotter.data.source.remote

import com.D121211014.castharrypotter.data.response.GetCharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

//    https://pixabay.com/api/?key=41249929-965ed05871767e5081c2a3b07&q=HarryPotter&image_type=photo&pretty=true
    @GET("api/")
    suspend fun getCharacter(
        @Query("key") key:String = "41249929-965ed05871767e5081c2a3b07",
        @Query("q") q:String = "HarryPotter",
        @Query("image_type") image_type:String = "photo",
        @Query("pretty") pretty:String = "true"
    ): GetCharacterResponse



}