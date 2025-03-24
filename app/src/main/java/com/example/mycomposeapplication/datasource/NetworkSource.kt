package com.example.mycomposeapplication.datasource

import android.util.Log
import com.example.mycomposeapplication.network.SSERetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody
import java.io.BufferedReader
import java.io.InputStreamReader

class NetworkSource {

    fun sendMessage(message: String): Flow<String> = flow {
        try {
            val response = SSERetrofitClient().service.getSSEStream().execute()
            val body: ResponseBody? = response.body()

            body?.let { it ->

                val reader = BufferedReader(InputStreamReader(it.byteStream()))
                try {
                    var line: String?
                    while (reader.readLine().also { li -> line = li } != null) {
                        println("$line")
                        if (line == "\n\n") println("###finish")
                        emit(line!!.removePrefix("data:"))
                    }
                } catch (e: Exception) {
                    println("SSE Error: ${e.message}")
                } finally {
                    println("close")
                    reader.close()
                }
            }
        } catch (e: Exception) {
            println("Network Error: ${e.message}")
        }
    }.flowOn(Dispatchers.IO)
}