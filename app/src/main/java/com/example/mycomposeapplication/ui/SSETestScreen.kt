package com.example.mycomposeapplication.ui

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SSETestScreen(viewModel: SSETestViewModel = viewModel()) {
    val text by viewModel.chatText.collectAsState()
    BaseScreen {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = text)
            Spacer(modifier = Modifier.height(8.dp))
            SendChatButton(onClick =  {
                viewModel.updateChat()
            })
        }
    }
}

@Composable
fun SendChatButton(onClick: ()->Unit) {
    Button(
        onClick = onClick
    ) {
        Text("Send Button(SSE)")
    }
}

@Composable
fun Modifier.keyboardDismissOnTap(): Modifier {
    val focusManager = LocalFocusManager.current
    return pointerInput(Unit) {
        detectTapGestures(onTap = {
            focusManager.clearFocus()
        })
    }
}
@Composable
fun BaseScreen(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .keyboardDismissOnTap()
            .imePadding()
    ) {
        content()
    }
}

