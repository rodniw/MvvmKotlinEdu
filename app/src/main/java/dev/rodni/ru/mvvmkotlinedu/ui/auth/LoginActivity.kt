package dev.rodni.ru.mvvmkotlinedu.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dev.rodni.ru.mvvmkotlinedu.R
import dev.rodni.ru.mvvmkotlinedu.data.db.AppDatabase
import dev.rodni.ru.mvvmkotlinedu.data.db.entity.User
import dev.rodni.ru.mvvmkotlinedu.data.network.MyApi
import dev.rodni.ru.mvvmkotlinedu.data.network.NetworkConnectionInterceptor
import dev.rodni.ru.mvvmkotlinedu.data.repositories.UserRepository
import dev.rodni.ru.mvvmkotlinedu.databinding.ActivityLoginBinding
import dev.rodni.ru.mvvmkotlinedu.ui.home.HomeActivity
import dev.rodni.ru.mvvmkotlinedu.util.hide
import dev.rodni.ru.mvvmkotlinedu.util.show
import dev.rodni.ru.mvvmkotlinedu.util.snackbar
import dev.rodni.ru.mvvmkotlinedu.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO: use KodeIn
        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = MyApi(networkConnectionInterceptor)
        val db = AppDatabase(this)
        val repository = UserRepository(api, db)
        val factory = AuthViewModelFactory(repository)

        //activitylogingbinding is some automatically generated class from xml activity_login
        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if(user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })

        progressbar_login.hide()
    }

    override fun onStarted() {
        progressbar_login.show()

        //root_layout.snackbar()
        //toast("Login onStarted")
    }

    override fun onSuccess(user: User) {
        progressbar_login.hide()
    }

    override fun onFailure(message: String) {
        progressbar_login.hide()

        root_layout.snackbar(message)
        //toast(message)
    }

}
