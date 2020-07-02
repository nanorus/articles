package com.nanorus.articles.app

import androidx.multidex.MultiDexApplication
import io.reactivex.plugins.RxJavaPlugins


class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler { throwable: Throwable? -> }
        instance = this
    }

    override fun onTerminate() {
        super.onTerminate()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}