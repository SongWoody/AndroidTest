package com.example.mycomposeapplication.repository

import com.example.mycomposeapplication.datasource.NetworkSource
import kotlinx.coroutines.flow.Flow

class ChatRepository(
    private val dataSource: NetworkSource
) {

    fun sendMessage(message: String): Flow<String> {
        return dataSource.sendMessage(message)
    }
}