package com.example.mycomposeapplication.ui.screen.chatlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycomposeapplication.ui.BaseScreen
import kotlinx.serialization.Serializable

@Serializable
object ChatListScreen

@Composable
fun ChatListScreen(
    viewModel: ChatListViewModel = viewModel()
) {
    val chatList by viewModel.characterList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchCharacters()
    }

    BaseScreen {
        Column {
            Box(
                modifier = Modifier.fillMaxWidth().height(50.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Chat List Screen",
                    textAlign = TextAlign.Center
                )
            }
            Divider()
            chatList.forEach {
                Text(it.name)
            }
        }
    }
}