package dev.rodni.ru.mvvmkotlinedu.data.repositories

import dev.rodni.ru.mvvmkotlinedu.data.network.MyApi
import dev.rodni.ru.mvvmkotlinedu.data.network.SafeApiRequest
import dev.rodni.ru.mvvmkotlinedu.data.network.responses.AuthResponse
import retrofit2.Response

class UserRepository : SafeApiRequest(){

    suspend fun userLogin(email: String, password: String) : AuthResponse {

        return apiRequest { MyApi().userLogin(email, password) }
        //return
    }
}