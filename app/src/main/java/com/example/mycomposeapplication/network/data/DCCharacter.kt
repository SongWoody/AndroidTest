package com.example.mycomposeapplication.network.data

import kotlinx.serialization.Serializable

@Serializable
data class DCCharacter(
    val id: Long,
    val name: String,
)
