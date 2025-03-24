package com.example.mycomposeapplication.network

import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.BufferedReader
import java.io.InputStreamReader

class SSERetrofitClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl(Config.BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    val service = retrofit.create(SSEService::class.java)
}