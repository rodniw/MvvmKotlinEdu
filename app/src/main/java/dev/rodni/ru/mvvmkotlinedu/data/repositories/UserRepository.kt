package dev.rodni.ru.mvvmkotlinedu.data.repositories

import dev.rodni.ru.mvvmkotlinedu.data.network.MyApi
import dev.rodni.ru.mvvmkotlinedu.data.network.responses.AuthResponse
import retrofit2.Response

class UserRepository {

    suspend fun userLogin(email: String, password: String) : Response<AuthResponse> {
        return MyApi().userLogin(email, password)
    }
}