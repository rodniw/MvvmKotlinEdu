package dev.rodni.ru.mvvmkotlinedu.data.network

import android.content.Context
import android.net.ConnectivityManager
import dev.rodni.ru.mvvmkotlinedu.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(
    context: Context
) : Interceptor {

    private val applicationContext = context.applicationContext

    //use isInternetAvailable to throw an exception or proceed
    override fun intercept(chain: Interceptor.Chain): Response {

        if(!isInternetAvailable())
            throw NoInternetException("Check your internet connection")

        return chain.proceed(chain.request())
    }

    //check internet connection
    private fun isInternetAvailable() :  Boolean {

        //use AS to cast smth
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }

    }
}