package dev.rodni.ru.mvvmkotlinedu.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import dev.rodni.ru.mvvmkotlinedu.data.repositories.UserRepository

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
        val loginResponse = UserRepository().userLogin(email!!, password!!)
        authListener?.onSuccess(loginResponse)
    }
}