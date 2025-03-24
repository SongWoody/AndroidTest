package com.example.mycomposeapplication.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class DefaultClient {
    val jsonParser = Json {
        encodeDefaults = true
        explicitNulls = false
        ignoreUnknownKeys = true
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(Config.BASE_URL)
        .addConverterFactory(jsonParser.asConverterFactory("application/json".toMediaType()))
        .build()

    val service = retrofit.create(DefaultService::class.java)

}