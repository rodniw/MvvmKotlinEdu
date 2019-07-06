package dev.rodni.ru.mvvmkotlinedu.ui.home.profile

import androidx.lifecycle.ViewModel;
import dev.rodni.ru.mvvmkotlinedu.data.repositories.UserRepository

class ProfileViewModel(
    userRepository: UserRepository
) : ViewModel() {

    val user = userRepository.getUser()

}
