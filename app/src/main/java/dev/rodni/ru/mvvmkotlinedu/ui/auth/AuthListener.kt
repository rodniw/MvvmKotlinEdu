package dev.rodni.ru.mvvmkotlinedu.ui.auth

interface AuthListener {

    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)

}