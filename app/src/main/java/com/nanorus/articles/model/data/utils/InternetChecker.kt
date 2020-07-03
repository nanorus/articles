package com.nanorus.articles.model.data.utils

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService
import com.nanorus.articles.app.App
import java.io.IOException


class InternetChecker {
    companion object {
        fun hasInternetAccess(): Boolean {
            try {
                return isNetworkAvailable()
            } catch (ex: InterruptedException) {

            } catch (ex: IOException) {

            }
            return false
        }

        private fun isNetworkAvailable(): Boolean {
            val connectivityManager =
                App.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }
}