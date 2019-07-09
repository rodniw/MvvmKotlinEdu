package dev.rodni.ru.mvvmkotlinedu

import android.app.Application
import dev.rodni.ru.mvvmkotlinedu.data.db.AppDatabase
import dev.rodni.ru.mvvmkotlinedu.data.network.MyApi
import dev.rodni.ru.mvvmkotlinedu.data.network.NetworkConnectionInterceptor
import dev.rodni.ru.mvvmkotlinedu.data.repositories.QuotesRepository
import dev.rodni.ru.mvvmkotlinedu.data.repositories.UserRepository
import dev.rodni.ru.mvvmkotlinedu.ui.auth.AuthViewModelFactory
import dev.rodni.ru.mvvmkotlinedu.ui.home.profile.ProfileViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MyApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {

        import(androidXModule(this@MyApplication))

        //to pass the context we need to use instance() method
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }

        //repos
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from singleton { QuotesRepository(instance(), instance()) }

        //vmfactories
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }

    }

}