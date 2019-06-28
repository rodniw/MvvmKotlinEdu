package dev.rodni.ru.mvvmkotlinedu.ui.auth

import dev.rodni.ru.mvvmkotlinedu.data.db.entity.User

interface AuthListener {

    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message: String)

}