package com.example.mycomposeapplication.network

import com.example.mycomposeapplication.network.data.DCCharacter
import com.example.mycomposeapplication.network.data.UserExisting
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DefaultService {

    @GET("/users/{userId}/exist")
    suspend fun checkIfUserExists(@Path("userId") userId: Long): Response<UserExisting>

    @GET("/api/characters")
    suspend fun fetchCharacters(
        @Query("index") index: Int,
        @Query("count") count: Int
    ): List<DCCharacter>
}