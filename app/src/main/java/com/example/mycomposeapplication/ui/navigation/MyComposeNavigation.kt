package com.example.mycomposeapplication.ui.navigation

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.example.mycomposeapplication.ui.screen.characterlist.ChatListScreen
import com.example.mycomposeapplication.ui.screen.login.LoginScreen
import kotlinx.serialization.Serializable

@Serializable
object TestDialog

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    // Destination Changed Listener
    navController.addOnDestinationChangedListener { controller, destination, arguments ->
        Log.i("Songyungi", "### destination: $destination")
    }

    // Create Graph
    val navGraph = remember(navController) {
        navController.createGraph(startDestination = LoginScreen) {
            composable<LoginScreen> {
                LoginScreen(
                    onMove = {
                        navController.navigate(ChatListScreen) {
                            popUpTo(LoginScreen) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable<ChatListScreen> {
                ChatListScreen()
            }

            dialog<TestDialog> {
                Text(text = "Test Dialog")
            }
        }
    }

    NavHost(navController = navController, graph = navGraph, modifier = modifier)
}
