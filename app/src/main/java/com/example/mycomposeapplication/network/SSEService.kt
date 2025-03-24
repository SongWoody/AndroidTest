package com.example.mycomposeapplication.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Streaming

interface SSEService {

    @GET("event")
    @Streaming
    fun getSSEStream(): Call<ResponseBody>

}