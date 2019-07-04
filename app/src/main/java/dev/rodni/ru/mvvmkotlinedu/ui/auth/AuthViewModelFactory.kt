package dev.rodni.ru.mvvmkotlinedu.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.rodni.ru.mvvmkotlinedu.data.repositories.UserRepository

@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory(
    private val repository: UserRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(repository) as T
    }
}