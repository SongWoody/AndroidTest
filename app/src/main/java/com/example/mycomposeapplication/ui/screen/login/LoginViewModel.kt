package com.example.mycomposeapplication.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycomposeapplication.network.DefaultClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    private val _userExisting: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val userExisting: StateFlow<Boolean?> = _userExisting

    fun checkIfUserExists(userId: Long) {
        viewModelScope.launch {
            val response = DefaultClient().service.checkIfUserExists(userId)
            val userExisting = response.body()
            if (userExisting != null && userExisting.result == "exist") {
                _userExisting.value = true
            } else {
                _userExisting.value = false
            }
        }

    }
}