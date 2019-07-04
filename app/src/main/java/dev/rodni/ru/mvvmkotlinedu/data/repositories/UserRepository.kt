package dev.rodni.ru.mvvmkotlinedu.data.repositories

import dev.rodni.ru.mvvmkotlinedu.data.db.AppDatabase
import dev.rodni.ru.mvvmkotlinedu.data.db.entity.User
import dev.rodni.ru.mvvmkotlinedu.data.network.MyApi
import dev.rodni.ru.mvvmkotlinedu.data.network.SafeApiRequest
import dev.rodni.ru.mvvmkotlinedu.data.network.responses.AuthResponse
import retrofit2.Response

class UserRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest(){

    suspend fun userLogin(email: String, password: String) : AuthResponse {

        return apiRequest { api.userLogin(email, password) }
        //return
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)
}