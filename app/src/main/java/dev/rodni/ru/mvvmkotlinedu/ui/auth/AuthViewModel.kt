package dev.rodni.ru.mvvmkotlinedu.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {

    var email: String? = null
    var password: String? = null

    var authListener: AuthListener? = null

    fun onLoginBtnClick(view: View) {
        authListener.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            //onFailure
            authListener.onFailure("Invalid email or password")

            return
        }

        //success
        authListener.onSuccess()

    }
}