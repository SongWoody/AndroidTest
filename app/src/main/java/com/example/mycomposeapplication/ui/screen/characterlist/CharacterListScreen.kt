package com.example.mycomposeapplication.ui.screen.characterlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.rememberAsyncImagePainter
import com.example.mycomposeapplication.network.Config
import com.example.mycomposeapplication.ui.BaseScreen
import kotlinx.serialization.Serializable

@Serializable
object ChatListScreen

@Composable
fun ChatListScreen(
    viewModel: CharacterListViewModel = viewModel()
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
                Row(
                    modifier = Modifier.height(58.dp).padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(40.dp).clip(CircleShape),
                        painter = rememberAsyncImagePainter("${Config.BASE_URL}image/thumbnail/${it.id}"),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(it.name)
                }
            }
        }
    }
}