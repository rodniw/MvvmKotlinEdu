package dev.rodni.ru.mvvmkotlinedu.data.network.responses

import dev.rodni.ru.mvvmkotlinedu.data.db.entity.User

data class AuthResponse(
    val isSuccessful : Boolean?,
    val message : String?,
    val user : User?
)