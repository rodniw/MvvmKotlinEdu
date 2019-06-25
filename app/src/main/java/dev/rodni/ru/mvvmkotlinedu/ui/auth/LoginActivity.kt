package dev.rodni.ru.mvvmkotlinedu.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.rodni.ru.mvvmkotlinedu.R
import dev.rodni.ru.mvvmkotlinedu.util.toast

class LoginActivity : AppCompatActivity(), AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onStarted() {
        toast("Login onStarted")
    }

    override fun onSuccess() {
        toast("Login onSuccess")
    }

    override fun onFailure(message: String) {
        toast(message)
    }

}
