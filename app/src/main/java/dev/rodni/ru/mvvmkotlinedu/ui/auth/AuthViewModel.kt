package dev.rodni.ru.mvvmkotlinedu.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import dev.rodni.ru.mvvmkotlinedu.data.repositories.UserRepository
import dev.rodni.ru.mvvmkotlinedu.util.Coroutines

class AuthViewModel : ViewModel() {

    var email: String? = null
    var password: String? = null

    var authListener: AuthListener? = null

    fun onLoginBtnClick(view: View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            //onFailure
            authListener?.onFailure("Invalid email or password")

            return
        }

        //success
        //fix dependencies later using dependency injection kodein thing
        Coroutines.main {
            val response = UserRepository().userLogin(email!!, password!!)
            if (response.isSuccessful) {
                authListener?.onSuccess(response.body()?.user!!)
            } else {
                authListener?.onFailure("error code: ${response.code()}")
            }
        }
    }
}