package dev.rodni.ru.mvvmkotlinedu.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import dev.rodni.ru.mvvmkotlinedu.R
import dev.rodni.ru.mvvmkotlinedu.databinding.ActivityLoginBinding
import dev.rodni.ru.mvvmkotlinedu.util.toast

class LoginActivity : AppCompatActivity(), AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //activitylogingbinding is some automatically generated class from xml activity_login
        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.authListener = this
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
