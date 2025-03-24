package com.example.mycomposeapplication.ui.screen.chatlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycomposeapplication.network.DefaultClient
import com.example.mycomposeapplication.network.data.DCCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChatListViewModel: ViewModel() {
    private val _characterList = MutableStateFlow<List<DCCharacter>>(emptyList())
    val characterList: StateFlow<List<DCCharacter>> = _characterList.asStateFlow()

    fun fetchCharacters() {
        viewModelScope.launch {
            val response = DefaultClient().service.fetchCharacters(0, 10)
            _characterList.value = response
        }
    }
}