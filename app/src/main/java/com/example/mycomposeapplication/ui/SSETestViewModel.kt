package com.example.mycomposeapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycomposeapplication.datasource.NetworkSource
import com.example.mycomposeapplication.repository.ChatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SSETestViewModel: ViewModel() {
    private val chatRepo = ChatRepository(NetworkSource())

    private val _chatText = MutableStateFlow("")
    val chatText = _chatText.asStateFlow()

    fun updateChat() {
        viewModelScope.launch {
            _chatText.value = ""
            chatRepo.sendMessage("ee").collectLatest {
                _chatText.value += it
            }
        }
    }
}