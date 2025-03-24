package com.example.mycomposeapplication.ui.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycomposeapplication.ui.BaseScreen
import kotlinx.serialization.Serializable

@Serializable
object LoginScreen

@Composable
fun LoginScreen(onMove: ()->Unit, viewModel: LoginViewModel = viewModel()) {
    val userExisting by viewModel.userExisting.collectAsState()

    BaseScreen {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var textFieldVal by remember { mutableStateOf("1") }
            TextField(
                textFieldVal,
                onValueChange = {
                    textFieldVal = it
                }
            )
            Spacer(modifier = Modifier.height(30.dp))
            Button(onClick = {
//                onMove()
                viewModel.checkIfUserExists(textFieldVal.toLong())
            }) {
                Text("Login")
            }

            userExisting?.let {
                if (it) {
                    LaunchedEffect(Unit) { onMove() }
                } else {
                    Text("User does not exist")
                }
            }
        }
    }


}