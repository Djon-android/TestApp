package com.example.testapp.data.network

import com.example.testapp.data.network.model.CharacterContainerDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getListCharacter(
        @Query(QUERY_PARAM_PAGE) page: Int
    ): CharacterContainerDto

    companion object {
        private const val QUERY_PARAM_PAGE = "page"
    }
}