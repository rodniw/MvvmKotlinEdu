package dev.rodni.ru.mvvmkotlinedu.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import dev.rodni.ru.mvvmkotlinedu.data.repositories.UserRepository
import dev.rodni.ru.mvvmkotlinedu.util.ApiException
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

            try {
                val authResponse = UserRepository().userLogin(email!!, password!!)

                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)

            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            }

        }
    }
}