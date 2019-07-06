package dev.rodni.ru.mvvmkotlinedu.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import dev.rodni.ru.mvvmkotlinedu.data.repositories.UserRepository
import dev.rodni.ru.mvvmkotlinedu.util.ApiException
import dev.rodni.ru.mvvmkotlinedu.util.Coroutines
import dev.rodni.ru.mvvmkotlinedu.util.NoInternetException

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {

    var name: String? = null
    var email: String? = null
    var password: String? = null
    var passwordConfirm: String? = null

    var authListener: AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

    //LOGIN
    fun onLoginBtnClick(view: View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            //onFailure
            authListener?.onFailure("Invalid email or password")

            return
        }

        //success
        Coroutines.main {

            try {
                val authResponse = repository.userLogin(email!!, password!!)

                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)

            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetException) {
                authListener?.onFailure(e.message!!)
            }

        }
    }

    fun onRegPageIntent(view: View) {
        Intent(view.context, SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    //SIGN UP
    fun onSignUpBtnClick(view: View) {
        authListener?.onStarted()

        //onFailure
        if (name.isNullOrEmpty()) {
            authListener?.onFailure("Invalid name")
            return
        }

        if (email.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email")
            return
        }

        if (password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid password")
            return
        }

        if (password != passwordConfirm) {
            authListener?.onFailure("Please repeat the password correctly")
            return
        }

        //success
        Coroutines.main {

            try {
                val authResponse = repository.userSignUp(name!!, email!!, password!!)

                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)

            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetException) {
                authListener?.onFailure(e.message!!)
            }

        }
    }
}